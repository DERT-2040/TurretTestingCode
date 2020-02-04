package frc.robot.commands;

import frc.robot.subsystems.Turret;
import frc.robot.subsystems.VisionCommunication;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class setTurretVision extends CommandBase {
    private final Turret m_Turret;
    private final VisionCommunication m_VisionCommunication;
    private double x;
    
    public setTurretVision(Turret subsystem, VisionCommunication subsystemVision){
        m_Turret = subsystem;
        m_VisionCommunication = subsystemVision;
        addRequirements(m_Turret, m_VisionCommunication);
    }

    public void execute(){
        x = m_VisionCommunication.getArrayData()[2];
        if(x > 340 && x != 999){
            m_Turret.turnTurret(.10);
        }
        else if(x < 300){
            m_Turret.turnTurret(-.10);
        }
        else if(x >= 300 && x <= 340 && x != 999){
            m_Turret.turnTurret(0);
        }
    }

    public boolean isFinished(){
        return false;
    }
} 