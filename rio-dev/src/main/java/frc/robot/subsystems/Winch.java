package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Winch extends SubsystemBase {
    
    public static TalonSRX m_winch;

    public Winch () {

        m_winch = new TalonSRX(Constants.CAN_Winch);

    }
}