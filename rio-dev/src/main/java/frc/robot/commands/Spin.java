package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Timer;

/**
* Spin around command
*
* Pressing the buttons will run the command and completely turn the robot around
*
* 1 = Right
* 2 = Left
*
* Please feel free to modify, share, and revise all code in this class
* provided our name (FRC 4073), and all authors below are credited.
*
* @author josephtelaak
* 
* @date_created 1/15/2020
* @date_modified 1/21/2020
*
* @revision 02
**/

public class Spin {

    private static TalonSRX[] Motors;

    private static AnalogGyro Gyro;

    public static void addMotors(TalonSRX[] m_motors) {

      Motors[0] = m_motors[0];
      Motors[1] = m_motors[1];
      Motors[2] = m_motors[2];
      Motors[3] = m_motors[3];

    }

    public static void addGyro(AnalogGyro m_gyro) {

      Gyro = m_gyro;

    }

    public static void Spin180(int direction) {
        
      Gyro.reset();

      double startAngle = Gyro.getAngle();

      // Spins around 180 Degrees
      if (direction == 1) {

        // Activates all right motors
        while(Math.round(startAngle + Gyro.getAngle()) == 180) {

          Motors[0].set(ControlMode.PercentOutput, 1);
          Motors[2].set(ControlMode.PercentOutput, 1);

          Timer.delay(0.005);

        }
  
      } else if (direction == 2) {
  
        // Activates all left motors
        while(Math.round(startAngle - Gyro.getAngle()) == -180) {

          Motors[1].set(ControlMode.PercentOutput, 1);
          Motors[3].set(ControlMode.PercentOutput, 1);

          Timer.delay(0.005);

        }
        
  
      }
    }

    public static void Spin90(int direction) {
        
    Gyro.reset();

    double startAngle = Gyro.getAngle();

    // Spins around 90 Degrees
    if (direction == 1) {

      // Activates all right motors
      while(Math.round(startAngle + Gyro.getAngle()) == 90) {

        Motors[0].set(ControlMode.PercentOutput, 1);
        Motors[1].set(ControlMode.PercentOutput, 1);

        Timer.delay(0.005);

      }
      

    } else if (direction == 2) {

      // Activates all left motors
      while(Math.round(startAngle + Gyro.getAngle()) == 90) {

        Motors[0].set(ControlMode.PercentOutput, 1);
        Motors[1].set(ControlMode.PercentOutput, 1);

        Timer.delay(0.005);

      }

    }
  }

}