// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package first.robot;

import org.wpilib.framework.RobotBase;

/**
 * Robot-wide constants. Mechanism constants live with their subsystem. CAN ports are recorded in
 * {@code docs/robot-io.md}.
 */
public final class Constants {
  private Constants() {}

  /** How the code is running. */
  public enum Mode {
    /** On a real robot. */
    REAL,
    /** Physics simulation on a laptop. */
    SIM,
    /** Replaying a log file (AdvantageKit). */
    REPLAY
  }

  /** Mode to use when not on a real robot. Change to REPLAY to replay a log. */
  public static final Mode SIM_MODE = Mode.SIM;

  public static final Mode CURRENT_MODE = RobotBase.isReal() ? Mode.REAL : SIM_MODE;

  public static final class OperatorConstants {
    private OperatorConstants() {}

    public static final int DRIVER_CONTROLLER_PORT = 0;
  }
}
