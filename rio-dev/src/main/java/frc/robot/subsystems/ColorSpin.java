package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.ColorSensorV3;

import frc.robot.Constants;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorSpin extends SubsystemBase {

    // I2C Port on the RoboRIO
    public final I2C.Port i2cPort;

    // Create Color Sensor Object **THIS IS TEMPORARY)
    public static ColorSensorV3 m_Color;

    public static TalonSRX m_colorSpinMotor;
    public static Solenoid m_upwheel;
    public static Solenoid m_downwheel;

    public ColorSpin() {

        m_upwheel   = new Solenoid(0);
        m_downwheel = new Solenoid(1);

        i2cPort = I2C.Port.kOnboard;

        m_Color = new ColorSensorV3(i2cPort);

        m_colorSpinMotor = new TalonSRX(Constants.CAN_ColorSpin);
    }

}