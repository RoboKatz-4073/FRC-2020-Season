package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Winch extends SubsystemBase {
    
    public static TalonSRX m_winch;
    public static Solenoid m_upwinch;
    public static Solenoid m_downwinch;

    public Winch () {

        m_upwinch   = new Solenoid(2);
        m_downwinch = new Solenoid(3);

        m_winch = new TalonSRX(Constants.CAN_Winch);

    }
}