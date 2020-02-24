package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyroscope;

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
* @author hunterkuperman
* @author josephtelaak
* 
* @date_created 1/15/2020
* @date_modified 1/21/2020
*
* @revision 02
**/

public class Spin {

  public static void turnAngle(double speed, double angle) {
      
    double startAngle = Gyroscope.m_locationboi.getAngle();
    double trueAngle  = startAngle + angle;
    double angleLeft  = -startAngle + trueAngle;

    while (angleLeft > 3 || angleLeft < -3) {

      if (angleLeft >= 30 || angleLeft <= -30) {

        SmartDashboard.putNumber("Angle Left", angleLeft);
    
      }

      if (angleLeft < 0) {

        DriveTrain.m_rightFrontMotor.set(ControlMode.PercentOutput, -speed);
        DriveTrain.m_leftFrontMotor.set(ControlMode.PercentOutput, -speed);
        DriveTrain.m_rightBackMotor.set(ControlMode.PercentOutput, -speed);
        DriveTrain.m_leftBackMotor.set(ControlMode.PercentOutput, -speed);

      } else if (angleLeft > 0) {

        DriveTrain.m_rightFrontMotor.set(ControlMode.PercentOutput, speed);
        DriveTrain.m_leftFrontMotor.set(ControlMode.PercentOutput, speed);
        DriveTrain.m_rightBackMotor.set(ControlMode.PercentOutput, speed);
        DriveTrain.m_leftBackMotor.set(ControlMode.PercentOutput, speed);

      }

      angleLeft = -Gyroscope.m_locationboi.getAngle() + trueAngle;

      if (angleLeft <= 40 && angleLeft >= -40) {

        SmartDashboard.putNumber("Angle Left", angleLeft);

        if (angleLeft < 0) {

         DriveTrain.m_rightFrontMotor.set(ControlMode.PercentOutput, -(speed / (5.5 + (angleLeft / 10)) + 0.2));
         DriveTrain.m_leftFrontMotor.set(ControlMode.PercentOutput, -(speed / (5.5 + (angleLeft / 10)) + 0.2));
         DriveTrain.m_rightBackMotor.set(ControlMode.PercentOutput, -(speed / (5.5 + (angleLeft / 10)) + 0.2));
         DriveTrain.m_leftBackMotor.set(ControlMode.PercentOutput, -(speed / (5.5 + (angleLeft / 10)) + 0.2));

        } else if (angleLeft > 0) {

          DriveTrain.m_rightFrontMotor.set(ControlMode.PercentOutput, (speed / (5.5 - angleLeft / 10) + 0.2));
          DriveTrain.m_leftFrontMotor.set(ControlMode.PercentOutput, (speed / (5.5 - angleLeft / 10) + 0.2));
          DriveTrain.m_rightBackMotor.set(ControlMode.PercentOutput, (speed / (5.5 - angleLeft / 10) + 0.2));
          DriveTrain.m_leftBackMotor.set(ControlMode.PercentOutput, (speed / (5.5 - angleLeft / 10) + 0.2));

        }
      }

      angleLeft = -Gyroscope.m_locationboi.getAngle() + trueAngle;

      DriveTrain.m_rightFrontMotor.set(ControlMode.PercentOutput, 0);
      DriveTrain.m_leftFrontMotor.set(ControlMode.PercentOutput, 0);
      DriveTrain.m_rightBackMotor.set(ControlMode.PercentOutput, 0);
      DriveTrain.m_leftBackMotor.set(ControlMode.PercentOutput, 0);
          
      return;

    }
      
  }

}