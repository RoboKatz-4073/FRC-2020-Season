package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.ArcadeDriveCommand;

import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
    
    public static TalonSRX m_leftFrontMotor;
    public static TalonSRX m_rightFrontMotor;
    
    public static TalonSRX m_leftBackMotor;
    public static TalonSRX m_rightBackMotor;

    public static SpeedControllerGroup m_left;
    public static SpeedControllerGroup m_right;

    public static DifferentialDrive m_drive;

    public DriveTrain () {

        m_leftFrontMotor = new TalonSRX(Constants.CAN_LF);
        m_rightFrontMotor = new TalonSRX(Constants.CAN_RF);
        m_leftBackMotor = new TalonSRX(Constants.CAN_LB);
        m_rightBackMotor = new TalonSRX(Constants.CAN_RB);

        m_left = new SpeedControllerGroup((SpeedController)m_leftFrontMotor,  (SpeedController)m_leftBackMotor);
        m_right = new SpeedControllerGroup((SpeedController)m_rightFrontMotor,  (SpeedController)m_rightBackMotor);

        m_drive = new DifferentialDrive(m_left, m_right);

    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ArcadeDriveCommand());

    }
    
    public void drive(double forward, double turn) {

        m_drive.arcadeDrive(forward, turn);

    }    
}