package first.robot.commands;

import first.robot.subsystems.exampleroller.ExampleRoller;
import first.robot.subsystems.exampleroller.ExampleRoller.Goal;
import org.wpilib.command2.Command;
import org.wpilib.command2.Commands;

/** The one command factory for {@link ExampleRoller}. Commands only set goals. */
public final class ExampleRollerCommands {
  private ExampleRollerCommands() {}

  /** Intake while held; return to IDLE when released or interrupted. */
  public static Command intake(ExampleRoller roller) {
    return holdGoal(roller, Goal.INTAKE).withName("ExampleRoller.intake");
  }

  /** Eject while held; return to IDLE when released or interrupted. */
  public static Command eject(ExampleRoller roller) {
    return holdGoal(roller, Goal.EJECT).withName("ExampleRoller.eject");
  }

  private static Command holdGoal(ExampleRoller roller, Goal goal) {
    return Commands.startEnd(() -> roller.setGoal(goal), () -> roller.setGoal(Goal.IDLE), roller);
  }
}
