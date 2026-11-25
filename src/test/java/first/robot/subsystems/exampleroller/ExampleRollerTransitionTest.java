package first.robot.subsystems.exampleroller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import first.robot.subsystems.exampleroller.ExampleRoller.Goal;
import first.robot.subsystems.exampleroller.ExampleRoller.State;
import org.junit.jupiter.api.Test;

/**
 * One test per row of the transition table in specs/000-example-roller/spec.md. Pure-function
 * tests: no HAL, no hardware, no timing.
 */
class ExampleRollerTransitionTest {
  private static final double NORMAL_AMPS = 5.0;
  private static final double STALL_AMPS = ExampleRoller.STALL_CURRENT_AMPS + 1.0;

  private static State next(
      State current, Goal goal, boolean enabled, boolean hasPiece, double amps) {
    return ExampleRoller.nextState(current, goal, enabled, hasPiece, amps).state();
  }

  @Test
  void t1_disabledAlwaysGoesIdle() {
    for (State s : State.values()) {
      for (Goal g : Goal.values()) {
        assertEquals(State.IDLE, next(s, g, false, true, STALL_AMPS), s + "/" + g);
      }
    }
  }

  @Test
  void t2_stalledStaysStalledUntilGoalIdle() {
    assertEquals(State.STALLED, next(State.STALLED, Goal.INTAKE, true, false, NORMAL_AMPS));
    assertEquals(State.STALLED, next(State.STALLED, Goal.EJECT, true, false, NORMAL_AMPS));
  }

  @Test
  void t3_overCurrentGoesStalled() {
    assertEquals(State.STALLED, next(State.INTAKING, Goal.INTAKE, true, false, STALL_AMPS));
    assertEquals(State.STALLED, next(State.EJECTING, Goal.EJECT, true, false, STALL_AMPS));
  }

  @Test
  void t4_goalIdleGoesIdleAndResetsStall() {
    assertEquals(State.IDLE, next(State.INTAKING, Goal.IDLE, true, false, NORMAL_AMPS));
    assertEquals(State.IDLE, next(State.STALLED, Goal.IDLE, true, false, NORMAL_AMPS));
  }

  @Test
  void t5_goalEjectGoesEjecting() {
    assertEquals(State.EJECTING, next(State.HOLDING, Goal.EJECT, true, true, NORMAL_AMPS));
  }

  @Test
  void t6_goalIntakeWithoutPieceGoesIntaking() {
    assertEquals(State.INTAKING, next(State.IDLE, Goal.INTAKE, true, false, NORMAL_AMPS));
  }

  @Test
  void t7_goalIntakeWithPieceGoesHolding() {
    assertEquals(State.HOLDING, next(State.INTAKING, Goal.INTAKE, true, true, NORMAL_AMPS));
  }

  @Test
  void safeDefault_idleAndStalledOutputZero() {
    assertEquals(0.0, ExampleRoller.outputVoltsFor(State.IDLE));
    assertEquals(0.0, ExampleRoller.outputVoltsFor(State.STALLED));
  }
}
