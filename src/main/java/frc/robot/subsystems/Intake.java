package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Intake extends SubsystemBase {
  private static final int shooterMotorID = 5;
  private WPI_TalonSRX m_shooterMotor= new WPI_TalonSRX(shooterMotorID);

  //Basic start and stop motor control
  public void startMotor(){
    m_shooterMotor.set(-1);
  }

  public void stopMotor(){
    m_shooterMotor.set(0);
  }
}