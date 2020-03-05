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
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * A command that will turn the robot to the specified angle.
 */
public class PIDTurret extends PIDCommand {

  private final int angleError = VisionCommunication.finalValue + Turret.actualValue;
  private final Turret m_Turret;
  private final VisionCommunication m_VisionCommunication;
  /**
   * Turns to robot to the specified angle.
   *
   * @param targetAngleDegrees The angle to turn to
   * @param m_Turret              The drive subsystem to use
   * @param m_Vision
   */
  public PIDTurret(double targetAngleDegrees, Turret m_Turret, VisionCommunication m_Vision) {  
    super(
        new PIDController(Constants.kTurnP, Constants.kTurnI, Constants.kTurnD),
        // Close loop on heading
        m_Turret::angleError,
        // Set reference to target
        targetAngleDegrees,
        // Pipe output to turn robot
        output -> m_Turret.turnTurret(output),
        // Require the drive
        m_Turret);
        
    // Set the controller to be continuous (because it is an angle controller)
    getController().enableContinuousInput(-180, 180);
    // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
    // setpoint before it is considered as having reached the reference
    getController()
        .setTolerance(Constants.kTurnToleranceDeg, Constants.kTurnRateToleranceDegPerS);
  }

  @Override
  public boolean isFinished() {
    // End when the controller is at the reference.
    return getController().atSetpoint();
  }
}