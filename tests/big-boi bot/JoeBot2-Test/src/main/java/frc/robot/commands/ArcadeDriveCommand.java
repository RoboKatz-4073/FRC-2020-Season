package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ArcadeDriveCommand extends CommandBase {

    public ArcadeDriveCommand() {

    }

    // Called just before this Command runs the first time
    public void initialize() {

        Robot.m_drivetrain.SetPower(0, 0);

    }
 
    // Called repeatedly when this Command is scheduled to run
    public void execute() {

        double y = Robot.m_stickboi.getY();
        Robot.m_drivetrain.SetPower(y, y);

    }
 
    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {

        return (false);

    }
 
    // Called once after isFinished returns true
    protected void end() {
    }
 
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}