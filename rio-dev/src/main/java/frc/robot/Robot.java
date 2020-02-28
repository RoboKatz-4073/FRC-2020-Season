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
* @date_modified 2/28/2020
*
* @revision 09
**/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.BallArm;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ControlWheel;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyroscope;
import frc.robot.subsystems.Launcher;

import frc.robot.Constants;

import frc.robot.commands.CloseAuto;
import frc.robot.commands.FarAuto;
import frc.robot.commands.MOTD;

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
  public static ControlWheel m_controlwheel;
  public static BallArm m_ballarm;
  public static Launcher m_launcher;
  public static Climber m_climber;

  public static Command m_closeauto;
  public static Command m_farauto;

  public static Compressor compresser;

  private double Speed = .50;

  /**
   * Initialization code.
   */
  @Override
  public void robotInit() {

    MOTD.print();

    compresser = new Compressor(0);
    
    compresser.start();

    m_drivetrain = new DriveTrain();
    m_gyro = new Gyroscope();
    m_controlwheel = new ControlWheel();
    m_ballarm = new BallArm();
    m_launcher = new Launcher();
    m_climber = new Climber();

    m_closeauto = new CloseAuto(m_drivetrain);
    m_farauto = new FarAuto(m_drivetrain);

    m_chooser.setDefaultOption("Close Auto", kDefaultAuto);
    m_chooser.addOption("Far Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    m_robotContainer = new RobotContainer();

    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture("Front Camera", 0);
    camera.setResolution(640, 480);

    Gyroscope.m_locationboi.calibrate();

    Launcher.m_opengate.set(false);
    Launcher.m_closegate.set(true);

    ControlWheel.m_downwheel.set(true);
    ControlWheel.m_upwheel.set(false);

    Climber.m_upwinch.set(false);
    Climber.m_downwinch.set(true);

  }

  /**
   * This function is called every robot packet
   */
  @Override
  public void robotPeriodic() {

    CommandScheduler.getInstance().run();

    SmartDashboard.putString("Color", ControlWheel.getAsString(ControlWheel.getColor()));

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

    SmartDashboard.putString("FMS Color", ControlWheel.getAsString(ControlWheel.FMSColor));

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

  Launcher.m_opengate.set(m_bigstickboi.getRawButton(11));
  Launcher.m_closegate.set(m_bigstickboi.getRawButton(10));

  ControlWheel.m_downwheel.set(m_buttonboi.getRawButton(7));
  ControlWheel.m_upwheel.set(m_buttonboi.getRawButton(5));

  Climber.m_upwinch.set(m_buttonboi.getRawButton(4));
  Climber.m_downwinch.set(m_buttonboi.getRawButton(3));

  if (m_buttonboi.getRawButton(6)) {

    Climber.m_winch.set(ControlMode.PercentOutput, 1);

  } else if (m_buttonboi.getRawButton(8)) {

    Climber.m_winch.set(ControlMode.PercentOutput, -1);

  } 

  if (m_bigstickboi.getRawButton(1)) {

    Launcher.m_launcher.set(ControlMode.PercentOutput, 1);

  }

  if (m_buttonboi.getRawButton(1)) {

    ControlWheel.spinUntilFound();

  } else if (m_buttonboi.getRawButton(2)) {

    ControlWheel.m_colorSpinMotor.set(ControlMode.PercentOutput, 1);

  } else {

    ControlWheel.m_colorSpinMotor.set(ControlMode.PercentOutput, 0);

  }

  double lt = m_stickboi.getRawAxis(2);
  double rt = m_stickboi.getRawAxis(3);

  BallArm.m_ballarm.set(ControlMode.PercentOutput, 0);

  if (lt > 0.2 || rt > 0.2) {

    BallArm.m_ballarm.set(ControlMode.PercentOutput, 1);

  } else {

    BallArm.m_ballarm.set(ControlMode.PercentOutput, 0);

  }

  Speed = 0.50;

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

  if (m_stickboi.getBumper(Hand.kRight)){

    Gyroscope.turnAngle(Speed, 180);

  } else if (m_stickboi.getBumper(Hand.kLeft)) {

    Gyroscope.turnAngle(Speed, 90);

  }

  double Xstick = m_stickboi.getRawAxis(4) * Speed;
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

    Speed = 0.50;

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

    double Xstick = m_stickboi.getRawAxis(4) * Speed;
    double Ystick = m_stickboi.getRawAxis(1) * Speed;

     if (Ystick < 0.2 && Ystick > -0.2) {

      Ystick = 0;

    }

    if (Xstick < 0.2 && Xstick > -0.2) {

      Xstick = 0;

    }

    DriveTrain.rdrive(Ystick + Xstick);
    DriveTrain.ldrive(-Ystick + Xstick);

  }
}
