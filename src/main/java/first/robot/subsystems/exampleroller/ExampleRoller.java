package first.robot.subsystems.exampleroller;

import org.littletonrobotics.junction.Logger;
import org.wpilib.command2.SubsystemBase;
import org.wpilib.driverstation.RobotState;

/**
 * Reference subsystem showing the team pattern (AGENTS.md, Architecture). Copy its shape, not its
 * numbers. Spec: {@code specs/000-example-roller/spec.md}.
 *
 * <ul>
 *   <li>Public {@link Goal}: what commands ask for. Commands only call {@link #setGoal(Goal)}.
 *   <li>Package-private {@link State}: what is actually happening, computed every loop from goal +
 *       logged inputs by the pure function {@link #nextState}, which the transition tests call.
 *   <li>{@link #periodic()}: read inputs, compute state, apply the safe default, set outputs, log.
 * </ul>
 */
public class ExampleRoller extends SubsystemBase {
  /** What the rest of the robot wants the roller to do. */
  public enum Goal {
    IDLE,
    INTAKE,
    EJECT
  }

  /** What the roller is actually doing. Hidden from commands; visible to tests in this package. */
  enum State {
    IDLE,
    INTAKING,
    HOLDING,
    EJECTING,
    STALLED
  }

  /** Result of one transition evaluation: the new state and why. */
  record Transition(State state, String reason) {}

  // Limits and outputs. A real mechanism takes these from its spec.
  static final double STALL_CURRENT_AMPS = 40.0;

  /**
   * Current must stay over the limit this many loops in a row (5 x 20 ms = 0.1 s) before we call it
   * a stall. Every motor draws a short current spike when it starts, so an undebounced limit would
   * trip on every button press on a real robot.
   */
  static final int STALL_DEBOUNCE_LOOPS = 5;

  static final double INTAKE_VOLTS = 8.0;
  static final double HOLD_VOLTS = 1.0;
  static final double EJECT_VOLTS = -8.0;

  private final ExampleRollerIO io;
  private final ExampleRollerIOInputsAutoLogged inputs = new ExampleRollerIOInputsAutoLogged();

  private Goal goal = Goal.IDLE;
  private State state = State.IDLE;
  private String transitionReason = "startup";
  private int overCurrentLoops = 0;

  public ExampleRoller(ExampleRollerIO io) {
    this.io = io;
  }

  /** The only way commands change behavior. */
  public void setGoal(Goal goal) {
    this.goal = goal;
  }

  public Goal getGoal() {
    return goal;
  }

  /** True when the roller is holding a game piece (for the superstructure). */
  public boolean isHolding() {
    return state == State.HOLDING;
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("ExampleRoller", inputs);

    // Debounce counters are computed from logged inputs only, so replay gives the same answer.
    overCurrentLoops = countOverCurrentLoops(overCurrentLoops, inputs.currentAmps);
    boolean stallDetected = overCurrentLoops >= STALL_DEBOUNCE_LOOPS;

    // Enable state is logged and replayed by AdvantageKit. (2027: lives in RobotState, not
    // DriverStation.)
    Transition next = nextState(state, goal, RobotState.isEnabled(), inputs.hasPiece, stallDetected);
    state = next.state();
    transitionReason = next.reason();

    double outputVolts = outputVoltsFor(state);
    io.setVoltage(outputVolts);

    Logger.recordOutput("ExampleRoller/Goal", goal);
    Logger.recordOutput("ExampleRoller/State", state);
    Logger.recordOutput("ExampleRoller/TransitionReason", transitionReason);
    Logger.recordOutput("ExampleRoller/StallDetected", stallDetected);
    Logger.recordOutput("ExampleRoller/OutputVolts", outputVolts);
  }

  /**
   * Transition table from the spec. Rows are checked top to bottom; safety rows come first.
   * Pure function: no hardware, no time, no randomness, so every row has a unit test.
   */
  static Transition nextState(
      State current, Goal goal, boolean enabled, boolean hasPiece, boolean stallDetected) {
    if (!enabled) {
      return new Transition(State.IDLE, "robot disabled");
    }
    if (current == State.STALLED && goal != Goal.IDLE) {
      return new Transition(State.STALLED, "stalled; waiting for IDLE goal to reset");
    }
    if (stallDetected) {
      return new Transition(State.STALLED, "current over stall limit for 0.1 s");
    }
    return switch (goal) {
      case IDLE -> new Transition(State.IDLE, "goal IDLE");
      case EJECT -> new Transition(State.EJECTING, "goal EJECT");
      case INTAKE ->
          hasPiece
              ? new Transition(State.HOLDING, "piece detected")
              : new Transition(State.INTAKING, "goal INTAKE, no piece");
    };
  }

  /**
   * How many loops in a row the current has been over {@link #STALL_CURRENT_AMPS}. Resets to zero
   * as soon as the current drops back under the limit. Pure, so it has its own unit test.
   */
  static int countOverCurrentLoops(int previousLoops, double currentAmps) {
    return currentAmps > STALL_CURRENT_AMPS ? previousLoops + 1 : 0;
  }

  /** Output for each state. Any state that is not actively moving outputs zero (safe default). */
  static double outputVoltsFor(State state) {
    return switch (state) {
      case INTAKING -> INTAKE_VOLTS;
      case HOLDING -> HOLD_VOLTS;
      case EJECTING -> EJECT_VOLTS;
      case IDLE, STALLED -> 0.0;
    };
  }
}
