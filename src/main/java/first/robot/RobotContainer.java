// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package first.robot;

import first.robot.Constants.OperatorConstants;
import first.robot.commands.Autos;
import first.robot.commands.ExampleRollerCommands;
import first.robot.subsystems.exampleroller.ExampleRoller;
import first.robot.subsystems.exampleroller.ExampleRollerIO;
import first.robot.subsystems.exampleroller.ExampleRollerIOSim;
import org.wpilib.command2.Command;
import org.wpilib.command2.button.CommandGamepad;

/** Declares subsystems (with the right IO for the current mode) and operator bindings. */
public class RobotContainer {
  private final ExampleRoller exampleRoller;

  private final CommandGamepad driverController =
      new CommandGamepad(OperatorConstants.DRIVER_CONTROLLER_PORT);

  public RobotContainer() {
    switch (Constants.CURRENT_MODE) {
      case SIM -> exampleRoller = new ExampleRoller(new ExampleRollerIOSim());
      // REAL: no hardware IO yet, so the roller is inert on a real robot.
      // REPLAY: inputs come from the log, so the IO does nothing.
      default -> exampleRoller = new ExampleRoller(new ExampleRollerIO() {});
    }
    configureBindings();
  }

  private void configureBindings() {
    driverController.faceRight().whileTrue(ExampleRollerCommands.intake(exampleRoller));
    driverController.faceDown().whileTrue(ExampleRollerCommands.eject(exampleRoller));
  }

  public Command getAutonomousCommand() {
    return Autos.none();
  }
}
