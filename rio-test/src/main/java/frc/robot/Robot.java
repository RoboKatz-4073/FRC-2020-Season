/*
@author hunterkuperman
@author josephtelaak
*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

import java.util.concurrent.TimeUnit;

import edu.wpi.first.wpilibj.AnalogGyro;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;

public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  // Xbox Controller
  private XboxController m_stickboi;

  // Logitech Joystick Controller
  private Joystick m_bigstickboi;

  // PXN Controller
  private XboxController m_buttonboi;

  // I2C Port on the RoboRIO
  private final I2C.Port i2cPort = I2C.Port.kOnboard;

  // Create Talon SRX Motor Controller Objects
  TalonSRX RightFront = new TalonSRX(4);
  TalonSRX LeftFront  = new TalonSRX(1); 
  TalonSRX RightBack  = new TalonSRX(2); 
  TalonSRX LeftBack   = new TalonSRX(3); 

  public double Red;
  public double Blue;
  public double Green;
  public String colorboi;

  // Create Color Sensor Object  **THIS IS TEMPORARY)
  public final ColorSensorV3 m_Color = new ColorSensorV3(i2cPort);

  // Sensors
  private AnalogGyro m_locationboi;

  // Operation mode
  private double operationMode = 1;

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

    // Create Gyro
    m_locationboi = new AnalogGyro(0);
  
    // Initialize Gyro
    m_locationboi.initGyro();
    m_locationboi.calibrate();

    // Add Gyro to Spin
    //Spin.addGyro(m_locationboi);

    // Set Output Levels
    RightFront.set(ControlMode.PercentOutput, 0);
    LeftFront.set(ControlMode.PercentOutput, 0);
    RightBack.set(ControlMode.PercentOutput, 0);
    LeftBack.set(ControlMode.PercentOutput, 0);

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

    // Grab color
    Color dColor = m_Color.getColor();

    Red   = dColor.red;
    Blue  = dColor.blue;
    Green = dColor.green;

    if (Red <= 0.23 && Green >= 0.5 && Blue >= 0.19) {

     colorboi = "Green";

    } else if (Red <= 0.25 && Green <= 0.52 && Blue >= 0.34) {

     colorboi = "Blue";

    } else if (Red >= 0.4 && Green >= 0.3 && Green <= 0.45 && Blue <= 0.19) {

     colorboi = "Red";

    } else if (Red >= 0.27 && Green >= 0.5 && Blue >= 0.09 && Blue <= 0.18) {

     colorboi = "Yellow";

    } else {
      colorboi = "Grey";
    }

    // Display Color Values on DriverStation
    SmartDashboard.putNumber("Red", dColor.red);
    SmartDashboard.putNumber("Green", dColor.green);
    SmartDashboard.putNumber("Blue", dColor.blue);

    SmartDashboard.putString("Color", colorboi);

  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
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
//  @Override
//  public void teleopPeriodic() {
//    
//    double Speed = 0.50;
//
//    // Macros
//    if (m_stickboi.getYButton()) {
//
//      // 15% Speed
//      Speed = 0.15;
//
//    } else if (m_stickboi.getBButton()) {
//
//      // 25% Speed
//      Speed = 0.25;
//
//    } else if (m_stickboi.getAButton()) {
//
//      // 75% Speed
//      Speed = 0.75;
//
//    } else if (m_stickboi.getXButton()) {
//
//      // Max Speed
//      Speed = 1;
//
//    } else if (m_stickboi.getStartButton()) {
//
//      Spin.Spin180(1);
//
//    }
//
//    double Xstick = m_stickboi.getRawAxis(0) * Speed;
//    double Ystick = m_stickboi.getRawAxis(1) * Speed;
//    double XX     = m_stickboi.getRawAxis(4) * Speed;
//
//     if (Ystick < 0.2 && Ystick > -0.2) {
//
//      Ystick = 0;
//
//    }
//
//    if (Xstick < 0.2 && Xstick > -0.2) {
//
//      Xstick = 0;
//
//    } 
//
//    if (m_stickboi.getRawAxis(4) > 0.2 || m_stickboi.getRawAxis(4) < -0.2) {
//
//    RightFront.set(ControlMode.PercentOutput, XX);
//    LeftFront.set(ControlMode.PercentOutput, XX);
//    RightBack.set(ControlMode.PercentOutput, -XX * 1.07);
//    LeftBack.set(ControlMode.PercentOutput, -XX);
//
//    } else {
//    RightFront.set(ControlMode.PercentOutput, Ystick + Xstick);
//    LeftFront.set(ControlMode.PercentOutput, -Ystick + Xstick);
//    RightBack.set(ControlMode.PercentOutput, Ystick + Xstick);
//    LeftBack.set(ControlMode.PercentOutput, -Ystick + Xstick);
//    }
//  }

  /**
   * This function is called periodically during test mode.
   */
//  @Override
//  public void testPeriodic() {
//
//    double speed = .25;
//
//    double Xstick = m_stickboi.getRawAxis(0) * Speed;
//    double Ystick = m_stickboi.getRawAxis(1) * Speed;
//    double XX     = m_stickboi.getRawAxis(4) * Speed;
//
//     if (Ystick < 0.2 && Ystick > -0.2) {
//
//      Ystick = 0;
//
//    }
//
//    if (Xstick < 0.2 && Xstick > -0.2) {
//
//    Xstick = 0;
//
//    } 
//
//    if (m_stickboi.getRawAxis(4) > 0.2 || m_stickboi.getRawAxis(4) < -0.2) {
//
//    RightFront.set(ControlMode.PercentOutput, XX);
//    LeftFront.set(ControlMode.PercentOutput, XX);
//    RightBack.set(ControlMode.PercentOutput, -XX * 1.07);
//    LeftBack.set(ControlMode.PercentOutput, -XX);
//
//    } else {
//
//    RightFront.set(ControlMode.PercentOutput, Ystick + Xstick);
//    LeftFront.set(ControlMode.PercentOutput, -Ystick + Xstick);
//    RightBack.set(ControlMode.PercentOutput, Ystick + Xstick);
//    LeftBack.set(ControlMode.PercentOutput, -Ystick + Xstick);
//
//    }
//  }
//
//}


 @Override
  public void teleopPeriodic() {

    if (m_buttonboi.getYButton()) {

      operationMode = 2;

    } else if (m_buttonboi.getBButton()) {

      operationMode = 1;

    }
    
    if (operationMode == 1) {

      SmartDashboard.putString("Mode", "Chassis Driver");
      teleopOperationModeOne();

    } else if (operationMode == 2) {

      SmartDashboard.putString("Mode", "Game-Piece Driver");
      teleopOperationModeTwo();

    }

    if (m_buttonboi.getAButton()) {

    } else if (m_buttonboi.getXButton()) {

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
      //Spin.Spin180(1);

    } else if (m_stickboi.getBumper(Hand.kLeft)) {

      // Spin
      //Spin.Spin180(2);

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

      //Spin.Spin180(1);

    } else if (m_bigstickboi.getRawButton(4)) {

      //Spin.Spin90(2);

    } else if (m_bigstickboi.getRawButton(5)) {

      //Spin.Spin90(1);

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

    double Xstick = m_stickboi.getRawAxis(0);
    double Ystick = m_stickboi.getRawAxis(1);
    double XX     = m_stickboi.getRawAxis(4);

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
