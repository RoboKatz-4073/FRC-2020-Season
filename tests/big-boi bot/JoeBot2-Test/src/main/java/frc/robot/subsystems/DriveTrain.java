package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.ArcadeDriveCommand;

public class DriveTrain extends SubsystemBase {
    
    public static TalonSRX m_leftFrontMotor = new TalonSRX(4);
    public static TalonSRX m_rightFrontMotor = new TalonSRX(2);
    
    public static TalonSRX m_leftBackMotor = new TalonSRX(3);
    public static TalonSRX m_rightBackMotor = new TalonSRX(1);

    public static SpeedControllerGroup m_left = new SpeedControllerGroup((SpeedController)m_leftFrontMotor,  (SpeedController)m_leftBackMotor);
    public static SpeedControllerGroup m_right = new SpeedControllerGroup((SpeedController)m_rightFrontMotor,  (SpeedController)m_rightBackMotor);

    public static DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ArcadeDriveCommand());

    }
    
    public void drive(double forward, double turn) {

        m_drive.arcadeDrive(forward, turn);

    }    
}