package frc.robot.subsystems.AlgaeRoller;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AlgaeRollersubsystem extends SubsystemBase {

  private final SparkMax arm = new SparkMax(2, MotorType.kBrushless);
  private final SparkMax roller = new SparkMax(1, MotorType.kBrushless);
  private final SparkMaxConfig config = new SparkMaxConfig();

  private final PIDController pid = new PIDController(0.06, 0.0, 0.0);
  private final double armminpos = 0.0;
  private final double armmaxpos = 1000.0;
  public double armsetpoint = 100f;


  public AlgaeRollersubsystem() {
    config.signals.primaryEncoderPositionPeriodMs(5);
    config.inverted(false);
    config.idleMode(IdleMode.kBrake);
    // config.encoder.positionConversionFactor(1000);
    // config.encoder.velocityConversionFactor(1000);
    config.closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder).pid(1.0, 0, 0);

    roller.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    arm.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  public void setRollerSpeed(double volts) {
    roller.set(volts);
  }

  public void setArmSpeed(double volts) {
    arm.set(volts);
  }

  public void setArmPosition(double targetPosition) {
    double pidOutput = pid.calculate(arm.getEncoder().getPosition());
    arm.setVoltage(pidOutput);
  }

  public void setArmSetPoint(double newSetPoint) {
    if (newSetPoint >= armminpos && newSetPoint <= armmaxpos) {
      armsetpoint = newSetPoint;
    }
  }
}
