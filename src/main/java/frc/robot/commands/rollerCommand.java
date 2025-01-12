package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AlgaeRoller.AlgaeRollersubsystem;

public class rollerCommand extends Command {

  private final AlgaeRollersubsystem algaeRollersubsystem;
  private final double targetSetpoint;

  public rollerCommand(AlgaeRollersubsystem subsystem, double rollerspeed) {
    algaeRollersubsystem = subsystem;
    targetSetpoint = rollerspeed;
  }

  @Override
  public void initialize() {
    algaeRollersubsystem.setRollerSpeed(targetSetpoint);
  }

  @Override
  public void end(boolean interrupted) {
    algaeRollersubsystem.setRollerSpeed(0);
  }
}
