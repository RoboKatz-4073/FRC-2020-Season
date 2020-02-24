package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallArm extends SubsystemBase {
    
    public static TalonSRX m_ballarm;

    public BallArm () {

        m_ballarm = new TalonSRX(1);

    }
}