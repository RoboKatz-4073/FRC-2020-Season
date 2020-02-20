package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gyroscope extends SubsystemBase {

    public static ADXRS450_Gyro m_locationboi;

    public void Gyro() {

        // Create Gyro
        m_locationboi = new ADXRS450_Gyro();

    }

}