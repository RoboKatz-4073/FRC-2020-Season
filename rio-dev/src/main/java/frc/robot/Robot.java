/**
* Main robot class for our 2020 Season program testing platform
* Much of this code will be transfered over to the competition robot
*
* Please feel free to modify, share, and revise all code in this class
* provided our name (FRC 4073), and all authors below are credited.
*
* @author josephtelaak
* @author hunterkuperman
* 
* @date_created 1/4/2020
* @date_modified 1/15/2020
*
* @revision 05
**/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Constants;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  // Subsystems
  public static DriveTrain m_drivetrain;
  public static GyroSystem m_gyro;
  public static ColorSpin m_colorspinner;

  // Controllers
  public static XboxController m_stickboi;
  public static Joystick m_bigstickboi;
  public static XboxController m_buttonboi;

  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private static final String kTurnAuto = "Gyro";
  private String m_autoSelected;

  // Colors 
  private static final String kblue = "Blue";
  private static final String kgreen = "Green";
  private static final String kred = "Red";
  private static final String kyellow = "Yellow";
  private String colorScelected;

  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private final SendableChooser<String> m_color = new SendableChooser<>();

  /**
   * Initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    m_chooser.addOption("Gyro Auto", kTurnAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    m_color.setDefaultOption("Blue", kblue);

    m_robotContainer = new RobotContainer();

    // Subsystems
    m_drivetrain = new DriveTrain();
    m_gyro = new GyroSystem();
    m_colorspinner = new ColorSpin();

  }

  /**
   * This function is called every robot packet
   */
  @Override
  public void robotPeriodic() {

    CommandScheduler.getInstance().run();

    SmartDashboard.putData("Color", PutColor.getColor());

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

    // Controllers
    m_stickboi = new XboxController(m_stickboiport);
    m_bigstickboi = new Joystick(m_bigstickboiport);
    m_buttonboi = new XboxController(m_buttonboiport);

    // Initialize Gyro\
    m_locationboi.calibrate();
    heading = m_locationboi.getAngle();

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

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

  @Override
  public void testInit() {

    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();

    // Controllers
    m_stickboi = new XboxController(m_stickboiport);

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {

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
}
