package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Launcher extends SubsystemBase {
    
    public static TalonSRX m_launcher;

    public Launcher () {

        m_launcher = new TalonSRX(Constants.CAN_Launcher);

    }
}