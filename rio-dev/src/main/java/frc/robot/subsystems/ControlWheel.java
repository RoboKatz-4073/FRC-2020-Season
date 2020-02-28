package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.ColorSensorV3;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ControlWheel extends SubsystemBase {

    // I2C Port on the RoboRIO
    public final I2C.Port i2cPort;

    public static ColorSensorV3 m_colorboi;

    public static TalonSRX m_colorSpinMotor;

    public static Solenoid m_upwheel;
    public static Solenoid m_downwheel;

    public static int FMSColor;

    public ControlWheel() {

        m_upwheel   = new Solenoid(Constants.SOLENOID_WheelUp);
        m_downwheel = new Solenoid(Constants.SOLENOID_WheelDown);

        i2cPort = I2C.Port.kOnboard;

        m_colorboi = new ColorSensorV3(i2cPort);

        m_colorSpinMotor = new TalonSRX(Constants.CAN_ColorSpin);

        FMSColor = getFMSColor();
    }

    public static String getAsString(int colorCode) {

        switch(colorCode) {
            case 0:
                return "Other";
            case 1:
                return "Green";
            case 2:
                return "Blue";
            case 3: 
                return "Red";
            case 4:
                return "Yellow";
            default:
                return "NaC";
        }

    }

    public static int getAsInt(String color) {

        switch(color) {
            case "Other":
                return 0;
            case "Green":
                return 1;
            case "Blue":
                return 2;
            case "Red":
                return 3;
            case "Yellow":
                return 4;
            default:
                return 69;
        }

    }

    private static int getFMSColor() {
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        
        if(gameData.length() > 0) {
  
            switch (gameData.charAt(0)) {
                
                case 'B' :
                    return 2;
                case 'G' :
                    return 1;
                case 'R' :
                    return 3;
                case 'Y' :
                    return 4;
                default :
                    return 69;
            }

        } else {
            return 69;
        }
    }

    public static int getColor() {
        // Grab color
        Color dColor = ControlWheel.m_colorboi.getColor();

        // 0: Other, 1: Green, 2: Blue, 3: Red, 4: Yellow

        if (dColor.red <= 0.23 && dColor.green >= 0.5 && dColor.blue >= 0.19) {
    
            return 1;

        } else if (dColor.red <= 0.25 && dColor.green <= 0.52 && dColor.blue >= 0.34) {

            return 2;
    
        } else if (dColor.red >= 0.4 && dColor.green >= 0.3 && dColor.green <= 0.45 && dColor.blue <= 0.19) {
    
            return 3;
    
        } else if (dColor.red >= 0.27 && dColor.green >= 0.5 && dColor.blue >= 0.09 && dColor.blue <= 0.18) {

            return 4;
    
        } else {

            return 0;

        }
    
    }

    public static void spinUntilFound(String color) {

        int targetColor = getAsInt(color);

        boolean colorFound = false;

        m_colorSpinMotor.set(ControlMode.PercentOutput, 1);

        while(!colorFound) {

            if (getColor() == targetColor) {
                
                m_colorSpinMotor.set(ControlMode.PercentOutput, 0);

                colorFound = true;

            }

        }

    }

    public static void spinUntilFound() {

        boolean colorFound = false;

        m_colorSpinMotor.set(ControlMode.PercentOutput, 1);

        while(!colorFound) {

            if (getColor() == FMSColor) {
                
                m_colorSpinMotor.set(ControlMode.PercentOutput, 0);

                colorFound = true;

            }

        }

    }

}