package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DriveTrain;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  public static DriveTrain m_drivetrain;

  public static XboxController m_stickboi;

  /**
   * Initialization code.
   */
  @Override
  public void robotInit() {

    m_robotContainer = new RobotContainer();

    m_drivetrain = new DriveTrain();

    m_stickboi = new XboxController(0);

  }

  /**
   * This function is called every robot packet
   */
  @Override
  public void robotPeriodic() {

    CommandScheduler.getInstance().run();

    double y = m_stickboi.getY();
    double x = m_stickboi.getX();

    if (y < 0.2 && y > -0.2) {

      y = 0;

    } 
    
    if (x < 0.2 && x > -0.2) {

      x = 0;

    }

    double Speed = 0.50;

    // Macros
    if (m_stickboi.getYButton()) {

      // 15% Speed
      Speed = 0.50;

    } else if (m_stickboi.getBButton()) {

      // 25% Speed
      Speed = 0.25;

    } else if (m_stickboi.getAButton()) {

      // 75% Speed
      Speed = 0.15;

    } else if (m_stickboi.getXButton()) {

      // Max Speed
      Speed = 1;

    }

    m_drivetrain.drive(Speed * y, Speed * x);

  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {

    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      
      m_autonomousCommand.schedule();

    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    
    if (m_autonomousCommand != null) {

      m_autonomousCommand.cancel();

    }

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {

    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
