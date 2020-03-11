/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import frc.robot.subsystems.Turret;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.VisionCommunication;
import frc.robot.Constants;

/**
 * A command that will turn the robot to the specified angle.
 */
public class PIDTurret extends PIDCommand {
  /**
   * Turns to robot to the specified angle.
   *
   * @param k_targetAngleDegrees The angle to turn to
   * @param k_Turret              The drive subsystem to use
   * @param k_Vision
   */
  private double pwmPosition;
  private double angleError;

  public PIDTurret(double k_targetAngleDegrees, Turret k_Turret, VisionCommunication k_Vision) { 
    super(
        new PIDController(Constants.kTurnP, Constants.kTurnI, Constants.kTurnD),
        // Close loop on heading
        k_Turret::getPwmPosition,
        // Set reference to target
        k_targetAngleDegrees,
        // Pipe output to turn robot
        output -> k_Turret.turnTurret(output),
        // Require the drive
        k_Turret
        );
    
    pwmPosition =  k_Turret.getPwmPosition();
    angleError = k_Vision.getAngleAprox() + pwmPosition;
    k_targetAngleDegrees = pwmPosition;
    

    // Set the controller to be continuous (because it is an angle controller)
    getController().enableContinuousInput(-180, 180);
    // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
    // setpoint before it is considered as having reached the reference
    getController().setTolerance(Constants.kTurnToleranceDeg, Constants.kTurnRateToleranceDegPerS);
  }

  @Override
  public boolean isFinished() {
    // End when the controller is at the reference.
    return getController().atSetpoint();
  }
}