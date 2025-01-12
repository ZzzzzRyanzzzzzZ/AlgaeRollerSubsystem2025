package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AlgaeRoller.AlgaeRollersubsystem;

public class armsetpoint extends Command {

  private final AlgaeRollersubsystem algaeRollersubsystem;
  private final double targetSetpoint;

  public armsetpoint(AlgaeRollersubsystem subsystem, double setpoint) {
    algaeRollersubsystem = subsystem;
    targetSetpoint = setpoint;
  }

  @Override
  public void initialize() {
    algaeRollersubsystem.setArmSetPoint(targetSetpoint);
  }

  @Override
  public void end(boolean interrupted) {
    algaeRollersubsystem.setArmPosition(0);
  }
}
