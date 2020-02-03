package frc.robot.commands;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class StopShoot extends CommandBase {
    private final Shooter m_Shooter;

    public StopShoot(Shooter subsystem){
        m_Shooter = subsystem;
        addRequirements(m_Shooter);
    }

    public void execute(){
        m_Shooter.stopMotor();
    }    

    public boolean isFinished(){
        return true;
    }
}
