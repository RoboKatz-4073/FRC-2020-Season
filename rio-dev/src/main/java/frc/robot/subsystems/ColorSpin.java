package frc.robot.subsystems;

public class ColorSpin extends SubsystemBase {

    // I2C Port on the RoboRIO
    public final I2C.Port i2cPort;

    // Create Color Sensor Object **THIS IS TEMPORARY)
    public static ColorSensorV3 m_Color;

    public static TalonSRX m_colorSpinMotor;

    public ColorSpin(int CAN_ColorSpin) {

        i2cPort = I2C.Port.kOnboard;

        m_color = new ColorSensorV3(i2cPort);

        m_colorSpinMotor = TalonSRX(CAN_ColorSpin);
    }

}