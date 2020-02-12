package frc.robot.subsystems;

public class GyroSystem extends SubsystemBase {

    public static ADXRS450_Gyro m_locationboi;

    public GyroSystem() {

        // Create Gyro
        m_locationboi = new ADXRS450_Gyro();

    }

}