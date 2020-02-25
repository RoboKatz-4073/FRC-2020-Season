package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Launcher extends SubsystemBase {
    
    public static TalonSRX m_launcher;
    public static Solenoid m_opengate;
    public static Solenoid m_closegate;

    public Launcher () {

        m_opengate  = new Solenoid(5);
        m_closegate = new Solenoid(4);
        m_launcher  = new TalonSRX(Constants.CAN_Launcher);

    }
}