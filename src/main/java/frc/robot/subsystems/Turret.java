package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Turret extends SubsystemBase {

  private static final int turretMotorID = 4;
  private WPI_TalonSRX m_turretMotor = new WPI_TalonSRX(turretMotorID);

  //Drives the Turret with a deadband
  public void turnTurretDeadband(double turnSpeed){
    if(turnSpeed <= 0.05 && turnSpeed >= -0.05){
      turnSpeed = 0;
    }
    m_turretMotor.set(turnSpeed * 0.25);
  }

  //Drives the turret without a deadband
  public void turnTurret(double turnSpeed){
    m_turretMotor.set(turnSpeed);
  }
}