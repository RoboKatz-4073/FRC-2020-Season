package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.ArcadeDriveCommand;

public class DriveTrain extends SubsystemBase {
    
    public static TalonSRX m_leftFrontMotor = new TalonSRX(4);
    public static TalonSRX m_rightFrontMotor = new TalonSRX(2);
    
    public static TalonSRX m_leftBackMotor = new TalonSRX(3);
    public static TalonSRX m_rightBackMotor = new TalonSRX(1);
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ArcadeDriveCommand());
    }
    
    public void SetPower(double leftPower, double rightPower) {
        m_leftFrontMotor.set(ControlMode.PercentOutput, leftPower);
        m_leftBackMotor.set(ControlMode.PercentOutput, leftPower);

        m_rightFrontMotor.set(ControlMode.PercentOutput, rightPower);
        m_rightBackMotor.set(ControlMode.PercentOutput, rightPower);
    }    

}