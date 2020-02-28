package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;

public class encoderRead extends CommandBase {
    private final Turret m_Turret;
    public encoderRead(Turret subsystem) {
        m_Turret = subsystem;
        addRequirements(m_Turret);
    }
    
    public void execute(){
        m_Turret.getPwmPosition();
    }

    public boolean isFinished(){
        return true;
    }
}