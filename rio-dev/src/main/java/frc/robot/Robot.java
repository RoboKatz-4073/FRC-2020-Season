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

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.ColorSpin;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyroscope;
import frc.robot.Constants;
import frc.robot.commands.CloseAuto;
import frc.robot.commands.FarAuto;
import frc.robot.commands.PutColor;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  // Controllers
  public static XboxController m_stickboi;
  public static Joystick m_bigstickboi;
  public static XboxController m_buttonboi;

  private static final String kDefaultAuto = "Close Auto";
  private static final String kCustomAuto = "Far Auto";
  private String m_autoSelected;

  // Colors
  private static final String kblue = "Blue";
  private static final String kgreen = "Green";
  private static final String kred = "Red";
  private static final String kyellow = "Yellow";

  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  public static DriveTrain m_drivetrain;
  public static Gyroscope m_gyro;
  public static ColorSpin m_colorspinner;

  public static Command m_putcolor;
  public static Command m_closeauto;
  public static Command m_farauto;

  /**
   * Initialization code.
   */
  @Override
  public void robotInit() {

    m_drivetrain = new DriveTrain();
    m_gyro = new Gyroscope();
    m_colorspinner = new ColorSpin();
    // private final BallArm m_ballarm;
    // private final Launcher m_launcher;

    m_putcolor = new PutColor(m_colorspinner);
    m_closeauto = new CloseAuto(m_drivetrain);
    m_farauto = new FarAuto(m_drivetrain);

    m_chooser.setDefaultOption("Close Auto", kDefaultAuto);
    m_chooser.addOption("Far Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    m_robotContainer = new RobotContainer();

    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture("Hunter", 0);
    camera.setResolution(640, 480);

    Gyroscope.m_locationboi.calibrate();

  }

  /**
   * This function is called every robot packet
   */
  @Override
  public void robotPeriodic() {

    CommandScheduler.getInstance().run();

    SmartDashboard.putString("Color", PutColor.Color());

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
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {

    m_autoSelected = m_chooser.getSelected();

    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

    switch (m_autoSelected) {
    case kCustomAuto:

      try {
        FarAuto.Run();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      break;

    case kDefaultAuto:

      try {
       CloseAuto.Run();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
  
        break;
    }

  }

  @Override
  public void teleopInit() {

    // Controllers
    m_stickboi = new XboxController(Constants.m_stickboiport);
    m_bigstickboi = new Joystick(Constants.m_bigstickboiport);
    m_buttonboi = new XboxController(Constants.m_buttonboiport);

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
    m_stickboi = new XboxController(Constants.m_stickboiport);

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
