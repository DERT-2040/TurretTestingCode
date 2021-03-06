/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
//import sun.management.Sensor;
import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import static edu.wpi.first.wpilibj.XboxController.Button;
//import com.ctre.phoenix.motorcontrol.SensorCollection;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Turret m_Turret = new Turret();
  private final Shooter m_Shooter = new Shooter();
  private final Intake m_Intake = new Intake();
  private final VisionCommunication m_VisionCommunication = new VisionCommunication();

  XboxController m_Controller = new XboxController(2);
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    m_Turret.setDefaultCommand(new JoystickTurret(m_Turret, () -> m_Controller.getX(GenericHID.Hand.kLeft)));
    //m_VisionCommunication.setDefaultCommand(new getVisionData(m_VisionCommunication));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(m_Controller, Button.kBumperLeft.value).whenPressed(new Shoot(m_Shooter)).whenReleased(new StopShoot(m_Shooter));
    new JoystickButton(m_Controller, Button.kBumperRight.value).whenPressed(new startIntake(m_Intake)).whenReleased(new stopIntake(m_Intake));
    new JoystickButton(m_Controller, Button.kA.value).whenPressed(new setTurretVision(m_Turret, m_VisionCommunication)).whenReleased(new stopTurret(m_Turret));
    new JoystickButton(m_Controller, Button.kB.value).whenPressed(new encoderRead(m_Turret));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomousk
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
