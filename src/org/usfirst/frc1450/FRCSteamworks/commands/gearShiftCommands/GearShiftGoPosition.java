package org.usfirst.frc1450.FRCSteamworks.commands.gearShiftCommands;

import org.usfirst.frc1450.FRCSteamworks.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearShiftGoPosition extends Command {

    public GearShiftGoPosition(int encPositionCmd, double timeoutSeconds) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearShift);
    	timeoutSeconds_m = timeoutSeconds;
    	encPositionCmd_m = encPositionCmd;
    }
    
    private boolean goLeft = false;
    
    private int loopCount = 0;
    private double timeoutSeconds_m;
    private int encPositionCmd_m;

    // Called just before this Command runs the first time
    protected void initialize() {
    	loopCount = 0;
    	if (encPositionCmd_m < Robot.gearShift.GetEncPosition())
    	{
    		goLeft = true;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (goLeft)
    	{
    		Robot.gearShift.teleopGearShift(-0.7);
    	}
    	else
    	{
    		Robot.gearShift.teleopGearShift(0.7);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (
        		(goLeft && (encPositionCmd_m > Robot.gearShift.GetEncPosition())) ||
        		((!goLeft) && (encPositionCmd_m < Robot.gearShift.GetEncPosition())) ||
        		(loopCount++ > timeoutSeconds_m / 0.02));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gearShift.teleopGearShift(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
