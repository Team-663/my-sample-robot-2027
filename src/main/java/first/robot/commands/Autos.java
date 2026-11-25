// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package first.robot.commands;

import org.wpilib.command2.Command;
import org.wpilib.command2.Commands;

/** Autonomous routines. Each auto gets its own spec after Kickoff. */
public final class Autos {
  private Autos() {}

  /** Placeholder auto that does nothing. */
  public static Command none() {
    return Commands.none().withName("Autos.none");
  }
}
