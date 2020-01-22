package frc.robot;

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

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

import java.util.concurrent.TimeUnit;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.commands.Spin;
import frc.robot.util.RaspberryPi;

import com.revrobotics.ColorSensorV3;

import frc.robot.util.RaspberryPi;
import frc.robot.util.RaspiCOMM;

import frc.robot.commands.R2D2;
import frc.robot.commands.Wakeup;
import frc.robot.commands.Spin;


public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  // Set default operation mode
  private int operationMode = 1;    // 1 = Xbox Controller, 2 = Aim Controller

  // Xbox Controller
  private XboxController m_stickboi;

  // Logitech Joystick Controller
  private Joystick m_bigstickboi;

  // PXN Controller
  private XboxController m_buttonboi;

  // I2C Port on the RoboRIO
  private final I2C.Port i2cPort = I2C.Port.kOnboard;

  // BeepBoop
  private R2D2 BeepBoop;

  // Raspberry Pi's
  private RaspberryPi pi_cam1;
  private RaspberryPi pi_cam2;
  private RaspberryPi pi_control;

  // Raspberry Pi Communication
  private RaspiCOMM cam1;
  private RaspiCOMM cam2;
  private RaspiCOMM control;

  // Create Talon SRX Motor Controller Objects
  private TalonSRX RightFront = new TalonSRX(4);
  private TalonSRX LeftFront  = new TalonSRX(1); 
  private TalonSRX RightBack  = new TalonSRX(2); 
  private TalonSRX LeftBack   = new TalonSRX(3); 

  // Array of Talons
  private TalonSRX[] MotorControllers = new TalonsSRX[3];

  // Built-in sensors
  private AnalogGyro m_locationboi;
  private BuiltInAccelerometer m_speedboi;

  // Create Color Sensor Object  **THIS IS TEMPORARY)
  public final ColorSensorV3 m_Color = new ColorSensorV3(i2cPort);

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    // Initialize Controllers
    m_stickboi    = new XboxController(0);
    m_bigstickboi = new Joystick(1);
    m_buttonboi   = new XboxController(2);

    // Initialize Pi's
    pi_cam1 = new RaspberryPi("pi", "Team4073!", "10.40.73.11");
    pi_cam2 = new RaspberryPi("pi", "Team4073!", "10.40.73.12");
    pi_control = new RaspberryPi("pi", "Team4073!", "10.40.73.13");

    // Initialize Comm
    cam1 = new RaspiCOMM(pi_cam1);
    cam2 = new RaspiCOMM(pi_cam2);
    control = new RaspiCOMM(pi_control);

    // Start beepboop
    BeepBoop = new R2D2(pi_cam1);
    BeepBoop.beepboop();

    // Set Output Levels
    RightFront.set(ControlMode.PercentOutput, 0);
    LeftFront.set(ControlMode.PercentOutput, 0);
    RightBack.set(ControlMode.PercentOutput, 0);
    LeftBack.set(ControlMode.PercentOutput, 0);

    // Add sensors
    m_locationboi = new AnalogGyro(0);
    m_speedboi = new BuiltInAccelerometer();

    // Initialize Gyro
    m_locationboi.initGyro();
    m_locationboi.calibrate();

    // Add motors to array
    MotorControllers[0] = RightFront;
    MotorControllers[1] = LeftFront;
    MotorControllers[2] = RightBack;
    MotorControllers[3] = LeftBack;

  }

  /**
   * Called every robot packet
   */
  @Override
  public void robotPeriodic() {
    
  // Grab color
  Color dColor = m_Color.getColor();

  // Display acceleration data
  SmartDashboard.putNumber("X", m_speedboi.getX());
  SmartDashboard.putNumber("Y", m_speedboi.getY());
  SmartDashboard.putNumber("Z", m_speedboi.getZ());

  // Display Gyro data
  SmartDashboard.putNumber("", m_locationboi.getAngle());

  // Display Color Values on DriverStation
  SmartDashboard.putNumber("Red", dColor.red);
  SmartDashboard.putNumber("Green", dColor.green);
  SmartDashboard.putNumber("Blue", dColor.blue);

  }

  /**
   * Autonomous Initialization
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
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    
    if (operationMode == 1) {

      teleopOperationModeOne();

    } else if (operationMode == 2) {

      teleopOperationModeTwo();

    }
    
  }

  public void teleopOperationModeOne() {

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

    } else if (m_stickboi.getBumper(Hand.kRight)) {

      // Spin
      Spin.Spin180(1);

    } else if (m_stickboi.getBumper(Hand.kLeft)) {

      // Spin
      Spin.Spin180(2);

    } else if (m_stickboi.getStartButton()) {

      // BeepBoop
      BeepBoop.beepboop();

    } else if (Math.round(m_stickboi.getTriggerAxis(Hand.kRight)) == 1) {

    } else if (Math.round(m_stickboi.getTriggerAxis(Hand.kRight)) == 1) {
      
    }

    double Xstick = m_stickboi.getRawAxis(0) * Speed;
    double Ystick = m_stickboi.getRawAxis(1) * Speed;
    double XX     = m_stickboi.getRawAxis(4) * Speed;

     if (Ystick < 0.2 && Ystick > -0.2) {

      Ystick = 0;

    }

    if (Xstick < 0.2 && Xstick > -0.2) {

      Xstick = 0;

    } 

    if (m_stickboi.getRawAxis(4) > 0.2 || m_stickboi.getRawAxis(4) < -0.2) {

    RightFront.set(ControlMode.PercentOutput, XX);
    LeftFront.set(ControlMode.PercentOutput, XX);
    RightBack.set(ControlMode.PercentOutput, -XX * 1.07);
    LeftBack.set(ControlMode.PercentOutput, -XX);

    } else {

    RightFront.set(ControlMode.PercentOutput, Ystick + Xstick);
    LeftFront.set(ControlMode.PercentOutput, -Ystick + Xstick);
    RightBack.set(ControlMode.PercentOutput, Ystick + Xstick);
    LeftBack.set(ControlMode.PercentOutput, -Ystick + Xstick);

    }

  }

  public void teleopOperationModeTwo() {

    // Default speed
    double Speed = .10;

    // Macros
    if (m_bigstickboi.getRawButton(6) || m_bigstickboi.getRawButton(3)) {

      Speed = 1;

    } else if (m_bigstickboi.getRawButton(7)) {

      Speed = .15;

    } else if (m_bigstickboi.getRawButton(10)) {

      Speed = .25;

    } else if (m_bigstickboi.getRawButton(12)) {

      Speed = .5;

    } else if (m_bigstickboi.getRawButton(2)) {

      Spin.Spin180(1);

    } else if (m_bigstickboi.getRawButton(4)) {

      Spin.Spin90(2);

    } else if (m_bigstickboi.getRawButton(5)) {

      Spin.Spin90(1);

    }
    
    double Xstick = m_bigstickboi.getRawAxis(0) * Speed;
    double Ystick = m_bigstickboi.getRawAxis(1) * Speed;

     if (Ystick < 0.2 && Ystick > -0.2) {

      Ystick = 0;

    }

    if (Xstick < 0.2 && Xstick > -0.2) {

      Xstick = 0;

    } 

    RightFront.set(ControlMode.PercentOutput, Ystick + Xstick);
    LeftFront.set(ControlMode.PercentOutput, -Ystick + Xstick);
    RightBack.set(ControlMode.PercentOutput, Ystick + Xstick);
    LeftBack.set(ControlMode.PercentOutput, -Ystick + Xstick);

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {

    double speed = .25;

    double Xstick = m_stickboi.getRawAxis(0) * Speed;
    double Ystick = m_stickboi.getRawAxis(1) * Speed;
    double XX     = m_stickboi.getRawAxis(4) * Speed;

     if (Ystick < 0.2 && Ystick > -0.2) {

      Ystick = 0;

    }

    if (Xstick < 0.2 && Xstick > -0.2) {

      Xstick = 0;

    } 

    if (m_stickboi.getRawAxis(4) > 0.2 || m_stickboi.getRawAxis(4) < -0.2) {

    RightFront.set(ControlMode.PercentOutput, XX);
    LeftFront.set(ControlMode.PercentOutput, XX);
    RightBack.set(ControlMode.PercentOutput, -XX * 1.07);
    LeftBack.set(ControlMode.PercentOutput, -XX);

    } else {

    RightFront.set(ControlMode.PercentOutput, Ystick + Xstick);
    LeftFront.set(ControlMode.PercentOutput, -Ystick + Xstick);
    RightBack.set(ControlMode.PercentOutput, Ystick + Xstick);
    LeftBack.set(ControlMode.PercentOutput, -Ystick + Xstick);

    }

  }

}
