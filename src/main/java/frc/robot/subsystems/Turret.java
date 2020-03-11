package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.SensorCollection;

public class Turret extends SubsystemBase {

  private static final int turretMotorID = 4;
  private WPI_TalonSRX m_turretMotor = new WPI_TalonSRX(turretMotorID);    
  private final SensorCollection sensors = m_turretMotor.getSensorCollection();
  private volatile int lastValue = Integer.MIN_VALUE;

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


  public int getPwmPosition() {
    int raw = sensors.getPulseWidthRiseToFallUs();
    if (raw == 0) {
      int lastValue = this.lastValue;
      if (lastValue == Integer.MIN_VALUE) {
        return 0;
      }
      return lastValue;
    }
    int actualValue = Math.min(4096, raw - 128);
    lastValue = actualValue;
    System.out.println(actualValue);
    return actualValue;
  }
  public void getAngle(){

  }
}