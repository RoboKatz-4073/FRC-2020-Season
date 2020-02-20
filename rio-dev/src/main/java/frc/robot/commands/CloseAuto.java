package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyroscope;
import frc.robot.commands.Spin;

public class CloseAuto extends CommandBase { 

public CloseAuto(DriveTrain driveTrain) {

}

public static void Run() throws InterruptedException {

    //This moves the robot forward at 50% speed, for 4 seconds
    DriveTrain.m_rightFrontMotor.set(ControlMode.PercentOutput, -0.5);
    DriveTrain.m_leftFrontMotor.set(ControlMode.PercentOutput, 0.5);
    DriveTrain.m_rightBackMotor.set(ControlMode.PercentOutput, -0.5);
    DriveTrain.m_leftBackMotor.set(ControlMode.PercentOutput, 0.5);
    Thread.sleep(4000);

    //This is just a pause to allow the wheels to brake
    DriveTrain.m_rightFrontMotor.set(ControlMode.PercentOutput, 0);
    DriveTrain.m_leftFrontMotor.set(ControlMode.PercentOutput, 0);
    DriveTrain.m_rightBackMotor.set(ControlMode.PercentOutput, 0);
    DriveTrain.m_leftBackMotor.set(ControlMode.PercentOutput, 0);
    Thread.sleep(250);

    //Here we turn the robot 90 degrees to the right
    Spin.turnAngle(0.65, 90);

    //This is just a pause to allow the wheels to brake
    DriveTrain.m_rightFrontMotor.set(ControlMode.PercentOutput, 0);
    DriveTrain.m_leftFrontMotor.set(ControlMode.PercentOutput, 0);
    DriveTrain.m_rightBackMotor.set(ControlMode.PercentOutput, 0);
    DriveTrain.m_leftBackMotor.set(ControlMode.PercentOutput, 0);
    Thread.sleep(250);

    //This moves the robot forward at 50% speed, for 3 seconds
    DriveTrain.m_rightFrontMotor.set(ControlMode.PercentOutput, -0.5);
    DriveTrain.m_leftFrontMotor.set(ControlMode.PercentOutput, 0.5);
    DriveTrain.m_rightBackMotor.set(ControlMode.PercentOutput, -0.5);
    DriveTrain.m_leftBackMotor.set(ControlMode.PercentOutput, 0.5);
    Thread.sleep(3000);

    //This is just a pause to allow the wheels to brake
    DriveTrain.m_rightFrontMotor.set(ControlMode.PercentOutput, 0);
    DriveTrain.m_leftFrontMotor.set(ControlMode.PercentOutput, 0);
    DriveTrain.m_rightBackMotor.set(ControlMode.PercentOutput, 0);
    DriveTrain.m_leftBackMotor.set(ControlMode.PercentOutput, 0);
    Thread.sleep(250);
    
}

}