package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyroscope;

public class CloseAuto extends CommandBase { 

public CloseAuto(DriveTrain driveTrain) {

}

public static void Run() throws InterruptedException {

    //This moves the robot forward at 50% speed, for 4 seconds
    DriveTrain.rdrive(-0.5);
    DriveTrain.ldrive(0.5);
    Thread.sleep(4000);

    //This is just a pause to allow the wheels to brake
    DriveTrain.stop();
    Thread.sleep(250);

    //Here we turn the robot 90 degrees to the right
    Gyroscope.turnAngle(0.65, 90);

    //This is just a pause to allow the wheels to brake
    DriveTrain.stop();
    Thread.sleep(250);

    //This moves the robot forward at 50% speed, for 3 seconds
    DriveTrain.rdrive(-0.5);
    DriveTrain.ldrive(0.5);
    Thread.sleep(3000);

    //This is just a pause to allow the wheels to brake
    DriveTrain.stop();
    Thread.sleep(250);
    
}

}