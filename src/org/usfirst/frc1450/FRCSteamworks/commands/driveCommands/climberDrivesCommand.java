package org.usfirst.frc1450.FRCSteamworks.commands.driveCommands;

import org.usfirst.frc1450.FRCSteamworks.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	This command switches driver joystick reference and selected webcam to drive using the rear of the robot as the front
 */
public class climberDrivesCommand extends Command {

    public climberDrivesCommand() {
    	requires(Robot.drives);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drives.setClimberDrives();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
