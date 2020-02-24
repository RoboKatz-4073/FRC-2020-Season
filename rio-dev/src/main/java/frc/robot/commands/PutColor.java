package frc.robot.commands;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorSpin;

public class PutColor extends CommandBase {

    public PutColor(ColorSpin m_colorspinner) {
	}

	public static String Color() {
    
      ColorSensorV3 m_Color = ColorSpin.m_Color;

    // Grab color
    Color dColor = m_Color.getColor();

    double Red = dColor.red;
    double Blue = dColor.blue;
    double Green = dColor.green;

    String colorboi;

    if (Red <= 0.23 && Green >= 0.5 && Blue >= 0.19) {

      colorboi = "Green";

    } else if (Red <= 0.25 && Green <= 0.52 && Blue >= 0.34) {

      colorboi = "Blue";

    } else if (Red >= 0.4 && Green >= 0.3 && Green <= 0.45 && Blue <= 0.19) {

      colorboi = "Red";

    } else if (Red >= 0.27 && Green >= 0.5 && Blue >= 0.09 && Blue <= 0.18) {

      colorboi = "Yellow";

    } else {
      colorboi = "Grey";
    }

    return colorboi;

    }

}