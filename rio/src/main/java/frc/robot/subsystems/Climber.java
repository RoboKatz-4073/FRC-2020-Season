package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Climber extends SubsystemBase {
    
    public static TalonSRX m_winch;

    public static Solenoid m_upwinch;
    public static Solenoid m_downwinch;

    public Climber() {

        m_upwinch   = new Solenoid(Constants.SOLENOID_ClimberUp);
        m_downwinch = new Solenoid(Constants.SOLENOID_ClimberDown);

        m_winch = new TalonSRX(Constants.CAN_Winch);

    }
}