// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1450.FRCSteamworks.commands.driveCommands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1450.FRCSteamworks.Robot;

/**
 *	This command uses feedback from the raspberry pi to determine what angle the robot should turn to face the target.
 *	It also takes a timeout to exit the function if it never completes.
 */
public class CameraBasedTurn extends Command {

    private double currentAngle;
    private double turnAngle;
    private double turnError;
    private double feedbackAngle;
    private double feedbackDistance;
    private double m_timeoutSeconds;
    private int loopCount = 0;
    private int timeout = 0;
    private double turnPercent = 0.0;
    private boolean isNotDone = true;
    private boolean lookForPiFeedback = true;
    private boolean hasFeedback = false;
    private int statePtr, stringIdx1, stringIdx2, stringIdx3;
    private String data;
    
    public CameraBasedTurn(double timeoutSeconds) {

        m_timeoutSeconds = timeoutSeconds;

        requires(Robot.drives);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	loopCount = 0;
    	turnAngle = Robot.drives.GetGyroAngle();	//make sure that target and current are equal so we don't move until we have a target
    	currentAngle = turnAngle;
    	turnPercent = SmartDashboard.getNumber("TurnPercent", 50);	//get voltage percent for turning reliably
    	if (turnPercent < 0.0)
    	{
    		turnPercent = 0.0;
    	}
    	else if (turnPercent > 100.0)
    	{
    		turnPercent = 100.0;
    	}
    	turnPercent = turnPercent / 100.0;
    	SmartDashboard.putString("Data", "");	//clear feedback to ensure that it is fresh
    	statePtr = 0;
    	timeout = 0;
    	SmartDashboard.putString("AutoState", "CameraTurn");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = Robot.drives.GetGyroAngle();				//gets current angle from 0 to 359
    	timeout++;
    	switch (statePtr)
    	{
    		case 0:
    			Robot.drives.teleopDrive(0.0,0.0);	//disable motors
    			if (Robot.drives.IsMoving())
    			{
    				loopCount = 0;
    			}
    			if (loopCount > (1.5 / 0.02))
    			{
    				statePtr++;
    				SmartDashboard.putString("Data", "");
    				stringIdx1 = 0;
    				stringIdx2 = 0;
    				stringIdx3 = 0;
    			}
    			break;
    		case 1:
    			data = SmartDashboard.getString("Data", "");		//expected feedback format is <angle,distance> ie <-15.3,14.7> is 15.3 degrees and 14.7 inches away
    			if (data.contains("<"))								//make sure data has expected opening character
		    	{
		    		stringIdx1 = data.indexOf("<");
		    	}
    			if (data.contains(","))								//make sure data has expected opening character
		    	{
		    		stringIdx2 = data.indexOf(",");
		    	}
    			if (data.contains(">"))								//make sure data has expected opening character
		    	{
		    		stringIdx3 = data.indexOf(">");
		    	}
    			if ((stringIdx1 < stringIdx2) && (stringIdx2 < stringIdx3))
    			{
    				feedbackAngle = Double.parseDouble(data.substring(stringIdx1 + 1, stringIdx2)) + SmartDashboard.getNumber("CamOffsetAngle", 3.0);
    				SmartDashboard.putNumber("piAngle", feedbackAngle);
    				SmartDashboard.putString("feedbackString", data);
    				feedbackDistance = Double.parseDouble(data.substring(stringIdx2 + 1, stringIdx3));
    				SmartDashboard.putNumber("piDistance", feedbackDistance);
    				turnAngle = currentAngle - feedbackAngle;
    				if (Math.abs(feedbackAngle) < 70.0)
	    			{
	    				statePtr++;
	    			}
    			}
    			break;
    			
    		case 2:
    			turnError = currentAngle - turnAngle;					//calculate current error from turn angle -390.1 to to 390.1
    	    	//make error the shortest possible angle
    	    	if (turnError > 180)
    	    	{
    	    		turnError -= 360;
    	    	}
    	    	else if (turnError < -180)
    	    	{
    	    		turnError += 360;
    	    	}
    	    	if (Math.abs(turnError) < 1.0)
    	    	{
    	    		//if within 3 degrees of target then done stop robot
    	    		Robot.drives.teleopDrive(0.0, 0.0);
    	    		statePtr++;
    	    		loopCount = 0;
    	    	}
    	    	else if (turnError > 0.0)
    	    	{
    	    		//turn left
    	    		Robot.drives.teleopDrive(-1.0 * turnPercent, 0.0);
    	    	}
    	    	else if (turnError < 0.0)
    	    	{
    	    		//turn right
    	    		Robot.drives.teleopDrive(turnPercent, 0.0);
    	    	}
    			break;
    		case 3:
    			break;
    	}
    	
    	SmartDashboard.putNumber("angleErr", turnError);
    	SmartDashboard.putNumber("angle", currentAngle);
    	// if not done turning to angle then do algorithm
    	loopCount++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//returns true if timed out but could also return true if locked in on angle and is done
    	return ((statePtr == 3) || (timeout > (m_timeoutSeconds / 0.02)));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drives.teleopDrive(0.0,0.0);	//disable motors
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}