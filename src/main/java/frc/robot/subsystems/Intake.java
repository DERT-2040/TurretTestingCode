package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Intake extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  private static final int shooterMotorID = 5;
  private WPI_TalonSRX m_shooterMotor= new WPI_TalonSRX(shooterMotorID);

  public void startMotor(){
    m_shooterMotor.set(0.25);
  }

  public void stopMotor(){
    m_shooterMotor.set(0);
  }
}