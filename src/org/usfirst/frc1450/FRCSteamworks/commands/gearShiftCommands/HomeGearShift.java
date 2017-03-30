package org.usfirst.frc1450.FRCSteamworks.commands.gearShiftCommands;

import org.usfirst.frc1450.FRCSteamworks.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class HomeGearShift extends Command {

    public HomeGearShift(double timeoutSeconds) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearShift);
    	timeoutSeconds_m = timeoutSeconds;
    }
    
    private boolean foundSensor = false;
    private int loopCount = 0;
    private double timeoutSeconds_m;

    // Called just before this Command runs the first time
    protected void initialize() {
    	loopCount = 0;
    	foundSensor = false;
    	Robot.gearShift.initGearShift();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (foundSensor)
    	{
    		if (Robot.gearShift.GetEncPosition() >= Robot.gearShift.middleCount + (Robot.gearShift.encPerInch * 0.5))
    		{
    			//SmartDashboard.putString("shiftState", "MovingToMiddle");
    			Robot.gearShift.teleopGearShift(-0.7);
    		}
    		else if (Robot.gearShift.GetEncPosition() <= Robot.gearShift.middleCount - (Robot.gearShift.encPerInch * 0.5))
    		{
    			//SmartDashboard.putString("shiftState", "MovingToMiddle");
    			Robot.gearShift.teleopGearShift(0.7);
    		}
    		else
    		{
    			//SmartDashboard.putString("shiftState", "Done");
    			Robot.gearShift.teleopGearShift(0.7);
    		}
    	}
    	else
    	{
    		if (Robot.gearShift.GetEncPosition() == 0)
    		{
    			foundSensor = true;
    			//SmartDashboard.putString("shiftState", "FoundHome");
    		}
    		else
    		{
    			//SmartDashboard.putString("shiftState", "MovingToHome");
    		}
    		Robot.gearShift.teleopGearShift(-0.7);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ((foundSensor && (Robot.gearShift.GetEncPosition() >= Robot.gearShift.middleCount)) ||
        		(loopCount++ >= timeoutSeconds_m / 0.02));
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
