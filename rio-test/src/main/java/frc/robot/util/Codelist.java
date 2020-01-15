package frc.robot.util;

/*
Table of codes sent to the RoboRIO

These numbers are appeneded to the beginning of the data to 
distinguish between the data sent to the RIO

This ideas was created to minimize lag and network traffic

Most processing that is not directly related to TeleOP and Autonomous 
is offloaded to a Raspberry Pi Co-Processor

**NOTE** Commands received by the controller take priority

Our Identifier Constant is 9

Format:
    Data Prefix + Constant + Data
    Ex.
        59255255255
        
        1. It is a color
        2. The color is black

@author josephtelaak
*/

public interface Codelist {
    public static final int IDENTIFIER_CONSTANT = 9;        // Used to detect end of identifier

    public static final int TURN_LEFT = 1;                  // Values provided afterward
    public static final int TURN_RIGHT = 2;                 // Values provided afterward

    public static final int TOO_FAR_LEFT = 3;               // Values provided afterward
    public static final int TOO_FAR_RIGHT = 4;              // Values provided afterward

    public static final int COLOR_DETECTED = 5;             // Correct RGB Code Found (Boolean 1 or 0)

    public static final int START_WHEEL = 6;                // Turn on launch wheel (Boolean 1 or 0)
    public static final int STOP_WHEEL = 7;                 // Turn off launch wheel (Boolean 1 or 0)


}