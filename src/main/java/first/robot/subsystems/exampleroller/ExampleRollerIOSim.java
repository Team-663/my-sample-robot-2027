package first.robot.subsystems.exampleroller;

/**
 * Minimal deterministic simulation: a first-order motor model with a fixed 20 ms step. It proves
 * logic and logging, not tuning. A real mechanism's plan picks a proper physics model.
 */
public class ExampleRollerIOSim implements ExampleRollerIO {
  private static final double LOOP_PERIOD_SECONDS = 0.02;
  private static final double FREE_SPEED_RPS_PER_VOLT = 8.0;
  private static final double TIME_CONSTANT_SECONDS = 0.05;
  private static final double CURRENT_AMPS_PER_VOLT_OF_SLIP = 6.0;

  private double appliedVolts = 0.0;
  private double velocityRps = 0.0;

  @Override
  public void updateInputs(ExampleRollerIOInputs inputs) {
    double targetRps = appliedVolts * FREE_SPEED_RPS_PER_VOLT;
    velocityRps += (targetRps - velocityRps) * (LOOP_PERIOD_SECONDS / TIME_CONSTANT_SECONDS);
    double slipVolts = appliedVolts - velocityRps / FREE_SPEED_RPS_PER_VOLT;

    inputs.motorConnected = true;
    inputs.velocityRps = velocityRps;
    inputs.appliedVolts = appliedVolts;
    inputs.currentAmps = Math.abs(slipVolts) * CURRENT_AMPS_PER_VOLT_OF_SLIP;
    inputs.hasPiece = false; // No game-piece sensor model yet.
  }

  @Override
  public void setVoltage(double volts) {
    appliedVolts = Math.max(-12.0, Math.min(12.0, volts));
  }
}
