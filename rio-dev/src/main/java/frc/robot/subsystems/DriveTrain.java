package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
    
    public static TalonSRX m_leftFrontMotor;
    public static TalonSRX m_rightFrontMotor;
    
    public static TalonSRX m_leftBackMotor;
    public static TalonSRX m_rightBackMotor;

    public DriveTrain () {

        m_leftFrontMotor = new TalonSRX(Constants.CAN_LF);
        m_rightFrontMotor = new TalonSRX(Constants.CAN_RF);
        m_leftBackMotor = new TalonSRX(Constants.CAN_LB);
        m_rightBackMotor = new TalonSRX(Constants.CAN_RB);

        m_leftBackMotor.set(ControlMode.Follower, Constants.CAN_LF);
        m_rightBackMotor.set(ControlMode.Follower, Constants.CAN_RF);


    }

    public static void stop() {

        m_rightFrontMotor.set(ControlMode.PercentOutput, 0);
        m_leftFrontMotor.set(ControlMode.PercentOutput, 0);


    }

    public static void ldrive(double demand) {

        m_leftFrontMotor.set(ControlMode.PercentOutput, demand);

    }

    public static void rdrive(double demand) {

        m_rightFrontMotor.set(ControlMode.PercentOutput, demand);

    }

    public static void forward(double speed) {
        
        m_rightFrontMotor.set(ControlMode.PercentOutput, speed);
        m_leftFrontMotor.set(ControlMode.PercentOutput, speed);

    }

    
        
}