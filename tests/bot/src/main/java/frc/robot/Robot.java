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

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Robot extends TimedRobot {

  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;

  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  // Xbox Controller
  private XboxController m_stickboi;

  // Create Talon SRX Motor Controller Objects
  TalonSRX RightFront;
  TalonSRX LeftFront;
  TalonSRX RightBack;
  TalonSRX LeftBack;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    // Initialize Controllers
    m_stickboi = new XboxController(0);

    try {

    // Set Output Levels
    RightFront.set(ControlMode.PercentOutput, 0);
    LeftFront.set(ControlMode.PercentOutput, 0);
    RightBack.set(ControlMode.PercentOutput, 0);
    LeftBack.set(ControlMode.PercentOutput, 0);

    } catch (NullPointerException e) {

      e.printStackTrace();

    }

    // Set up motors
    RightFront = new TalonSRX(2);  //2
    LeftFront = new TalonSRX(4);   //4
    RightBack = new TalonSRX(1);   //1
    LeftBack = new TalonSRX(3);    //3

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString line to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional comparisons to the
   * switch structure below with additional strings. If using the SendableChooser
   * make sure to add them to the chooser code above as well.
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
    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    
    double Speed = 0.50;

    // Macros
    if (m_stickboi.getYButton()) {

      // 15% Speed
      Speed = 0.15;

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

    

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {

  }
}

