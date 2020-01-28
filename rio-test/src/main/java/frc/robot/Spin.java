package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

    private static TalonSRX RightFront;
    private static TalonSRX RightBack;
    private static TalonSRX LeftFront;
    private static TalonSRX LeftBack;

    private static Gyro Gyro;

    public static void addGyro(Gyro m_gyro) {

      Gyro = m_gyro;

    }

    public static void addMotors(TalonSRX RF, TalonSRX RB, TalonSRX LF, TalonSRX LB) {

      RightFront = RF;
      RightBack = RB;
      LeftFront = LF;
      LeftBack = LB;

    }

    public static void turnAngle(double speed, double angle) {
        
      double startAngle = Gyro.getAngle();
      double trueAngle  = startAngle + angle;
      double angleLeft  = -startAngle + trueAngle;

  while (angleLeft > 3 || angleLeft < -3) {

      if (angleLeft >= 30 || angleLeft <= -30) {

        SmartDashboard.putNumber("Angle Left", angleLeft);

        if (angleLeft < 0) {

        RightFront.set(ControlMode.PercentOutput, -speed);
        LeftFront.set(ControlMode.PercentOutput, -speed);
        RightBack.set(ControlMode.PercentOutput, -speed);
        LeftBack.set(ControlMode.PercentOutput, -speed);

        } else if (angleLeft > 0) {

        RightFront.set(ControlMode.PercentOutput, speed);
        LeftFront.set(ControlMode.PercentOutput, speed);
        RightBack.set(ControlMode.PercentOutput, speed);
        LeftBack.set(ControlMode.PercentOutput, speed);

          }

      angleLeft = -Gyro.getAngle() + trueAngle;

      }

      if (angleLeft <= 40 && angleLeft >= -40) {

        SmartDashboard.putNumber("Angle Left", angleLeft);

        if (angleLeft < 0) {

        RightFront.set(ControlMode.PercentOutput, -(speed / (5.5 + (angleLeft / 10)) + 0.2));
        LeftFront.set(ControlMode.PercentOutput, -(speed / (5.5 + (angleLeft / 10)) + 0.2));
        RightBack.set(ControlMode.PercentOutput, -(speed / (5.5 + (angleLeft / 10)) + 0.2));
        LeftBack.set(ControlMode.PercentOutput, -(speed / (5.5 + (angleLeft / 10)) + 0.2));

        } else if (angleLeft > 0) {

        RightFront.set(ControlMode.PercentOutput, (speed / (5.5 - angleLeft / 10) + 0.2));
        LeftFront.set(ControlMode.PercentOutput, (speed / (5.5 - angleLeft / 10) + 0.2));
        RightBack.set(ControlMode.PercentOutput, (speed / (5.5 - angleLeft / 10) + 0.2));
        LeftBack.set(ControlMode.PercentOutput, (speed / (5.5 - angleLeft / 10) + 0.2));

          }

      angleLeft = -Gyro.getAngle() + trueAngle;


        }

      }


            RightFront.set(ControlMode.PercentOutput, 0);
            LeftFront.set(ControlMode.PercentOutput, 0);
            RightBack.set(ControlMode.PercentOutput, 0);
            LeftBack.set(ControlMode.PercentOutput, 0);

            return;


        }
        
  
      }
