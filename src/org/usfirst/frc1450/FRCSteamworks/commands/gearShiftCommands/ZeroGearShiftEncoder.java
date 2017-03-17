package org.usfirst.frc1450.FRCSteamworks.commands.gearShiftCommands;

import org.usfirst.frc1450.FRCSteamworks.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ZeroGearShiftEncoder extends Command {

    public ZeroGearShiftEncoder() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.gearShift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.gearShift.zeroEncoders();
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
