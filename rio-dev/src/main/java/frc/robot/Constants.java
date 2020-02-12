/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    // CAN ports for the motors
    public static final int CAN_LF = 4;  // Front Left
    public static final int CAN_RF = 2;  // Front Right

    public static final int CAN_LB = 3;  // Rear Left
    public static final int CAN_RB = 1;  // Rear Right

    // Controller Ports
    public static final int m_stickboiport = 0;
    public static final int m_bigstickboiport = 1;
    public static final int m_buttonboiport = 2;


}
