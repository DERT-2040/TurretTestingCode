package frc.robot.commands;

import frc.robot.subsystems.VisionCommunication;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class getVisionData extends CommandBase {
    private final VisionCommunication m_VisionCommunication;
    
    public getVisionData(VisionCommunication subsytem){
        m_VisionCommunication = subsytem;
        addRequirements(m_VisionCommunication);
    }

    public void execute(){
        System.out.println("pls");
        m_VisionCommunication.getArrayData();
        
    }

    public boolean isFinished(){
        return false;
    }
}