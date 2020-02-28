package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gyroscope extends SubsystemBase {

    public static ADXRS450_Gyro m_locationboi;

    public Gyroscope() {

        // Create Gyro
        m_locationboi = new ADXRS450_Gyro();

    }

    public static void turnAngle(double speed, double angle) {
      
        double startAngle = Gyroscope.m_locationboi.getAngle();
        double trueAngle  = startAngle + angle;
        double angleLeft  = -startAngle + trueAngle;
    
        while (angleLeft > 3 || angleLeft < -3) {
    
          if (angleLeft >= 30 || angleLeft <= -30) {
    
            SmartDashboard.putNumber("Angle Left", angleLeft);
        
          }
    
          if (angleLeft < 0) {

            DriveTrain.forward(-speed);
    
          } else if (angleLeft > 0) {
    
            DriveTrain.forward(speed);
    
          }
    
          angleLeft = -Gyroscope.m_locationboi.getAngle() + trueAngle;
    
          if (angleLeft <= 40 && angleLeft >= -40) {
    
            SmartDashboard.putNumber("Angle Left", angleLeft);
    
            if (angleLeft < 0) {

                DriveTrain.forward(-(speed / (5.5 + (angleLeft / 10)) + 0.2));
    
            } else if (angleLeft > 0) {

                DriveTrain.forward(speed / (5.5 - angleLeft / 10) + 0.2));
    
            }
          }
    
          angleLeft = -Gyroscope.m_locationboi.getAngle() + trueAngle;
    
          DriveTrain.stop();
              
          return;
    
        }
          
      }

}