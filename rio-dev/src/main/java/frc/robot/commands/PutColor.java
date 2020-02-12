package frc.root.commands;

public class PutColor {

    public static String getColor() {
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