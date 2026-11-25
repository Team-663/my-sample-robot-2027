package first.robot.subsystems.exampleroller;

import org.littletonrobotics.junction.AutoLog;

/**
 * Hardware boundary for the example roller. Every hardware read goes through {@link
 * ExampleRollerIOInputs} so it is logged and can be replayed.
 *
 * <p>Implementations: {@link ExampleRollerIOSim} (simulation) and the empty default (replay, or
 * real robot until hardware exists). A real implementation (for example a Phoenix 6 TalonFX on a
 * {@code CANPort}) is added by the mechanism's own spec and plan, and recorded in {@code
 * docs/robot-io.md}.
 */
public interface ExampleRollerIO {
  @AutoLog
  class ExampleRollerIOInputs {
    public boolean motorConnected = false;
    public double velocityRps = 0.0;
    public double appliedVolts = 0.0;
    public double currentAmps = 0.0;
    public boolean hasPiece = false;
  }

  /** Reads the latest sensor values into {@code inputs}. */
  default void updateInputs(ExampleRollerIOInputs inputs) {}

  /** Commands the motor voltage. Called once per loop by the subsystem, never by commands. */
  default void setVoltage(double volts) {}
}
