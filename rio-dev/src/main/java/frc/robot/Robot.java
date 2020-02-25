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

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.BallArm;
import frc.robot.subsystems.ColorSpin;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyroscope;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.Winch;
import frc.robot.Constants;
import frc.robot.commands.CloseAuto;
import frc.robot.commands.FarAuto;
import frc.robot.commands.PutColor;

public class Robot extends TimedRobot {

  private RobotContainer m_robotContainer;

  // Controllers
  public static XboxController m_stickboi;
  public static Joystick m_bigstickboi;
  public static XboxController m_buttonboi;

  private static final String kDefaultAuto = "Close Auto";
  private static final String kCustomAuto = "Far Auto";
  private String m_autoSelected;

  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  public static DriveTrain m_drivetrain;
  public static Gyroscope m_gyro;
  public static ColorSpin m_colorspinner;
  public static BallArm   m_ballarm;
  public static Launcher  m_launcher;
  public static Winch     m_winch;

  public static Command m_putcolor;
  public static Command m_closeauto;
  public static Command m_farauto;

  boolean drivemode = true;
  boolean shootmode = false;

  /**
   * Initialization code.
   */
  @Override
  public void robotInit() {

    m_drivetrain = new DriveTrain();
    m_gyro = new Gyroscope();
    m_colorspinner = new ColorSpin();
    m_ballarm = new BallArm();
    m_launcher = new Launcher();
    m_winch = new Winch();

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
        e.printStackTrace();
      }

      break;

    case kDefaultAuto:

      try {
       CloseAuto.Run();
      } catch (InterruptedException e) {
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

  Launcher.m_opengate.set(m_bigstickboi.getRawButton(11));
  Launcher.m_closegate.set(m_bigstickboi.getRawButton(10));

  ColorSpin.m_downwheel.set(m_bigstickboi.getRawButton(7));
  ColorSpin.m_upwheel.set(m_bigstickboi.getRawButton(5));

  Winch.m_upwinch.set(m_bigstickboi.getRawButton(9));
  Winch.m_downwinch.set(m_bigstickboi.getRawButton(10)); 

  if (m_bigstickboi.getRawButton(6)) {
    Winch.m_winch.set(ControlMode.PercentOutput, 1);
  } else if (m_bigstickboi.getRawButton(8)) {
    Winch.m_winch.set(ControlMode.PercentOutput, -1);
  } else {
    Winch.m_winch.set(ControlMode.PercentOutput, 0);
  }

  if (m_buttonboi.getXButtonPressed()) {
    drivemode = true;
    shootmode = false;
  }

  if (m_buttonboi.getAButtonPressed()) {
    shootmode = true;
    drivemode = false;
  }

    if (shootmode) {
      
    SmartDashboard.putString("Mode", "Shoot Mode");

    boolean shootbutton = m_bigstickboi.getRawButton(1);

      Launcher.m_launcher.set(ControlMode.PercentOutput, 0);

      if (shootbutton) {
        Launcher.m_launcher.set(ControlMode.PercentOutput, 1);
      }

    } else {

    SmartDashboard.putString("Mode", "Drive Mode");

    double lt = m_stickboi.getTriggerAxis(Hand.kLeft);
    double rt = m_stickboi.getTriggerAxis(Hand.kRight);

    BallArm.m_ballarm.set(ControlMode.PercentOutput, 0);

    if (lt > 0.5 || rt > 0.5) {
      BallArm.m_ballarm.set(ControlMode.PercentOutput, 1);
    } else {
      BallArm.m_ballarm.set(ControlMode.PercentOutput, 0);
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
      Speed = 0.75;
    } else if (m_stickboi.getXButton()) {
      // Max Speed
      Speed = 1;
    }

    double Xstick = m_stickboi.getRawAxis(0) * Speed;
    double Ystick = m_stickboi.getRawAxis(1) * Speed;

     if (Ystick < 0.2 && Ystick > -0.2) {

      Ystick = 0;

    }

    if (Xstick < 0.2 && Xstick > -0.2) {

      Xstick = 0;

    }

    DriveTrain.m_rightFrontMotor.set(ControlMode.PercentOutput, Ystick + Xstick);
    DriveTrain.m_rightBackMotor.set(ControlMode.PercentOutput, Ystick + Xstick);
    DriveTrain.m_leftFrontMotor.set(ControlMode.PercentOutput, -Ystick + Xstick);
    DriveTrain.m_leftBackMotor.set(ControlMode.PercentOutput, -Ystick + Xstick);


  }
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
    }
}
