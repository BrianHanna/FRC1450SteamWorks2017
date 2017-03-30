package org.usfirst.frc1450.FRCSteamworks.commands.driveCommands;

import org.usfirst.frc1450.FRCSteamworks.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *	This command uses arcade drive method but takes in to account feedback from the gyro to keep it driving straight
 *	it takes an input of encoder counts to travel at which point the command completes.  If something were to go wrong
 *	the command also takes an input of seconds for a timeout.
 */
public class AutonomousArcadeDrivePiDistMinusConst extends Command {

	private double m_encoderCount;
    private double m_timeoutSeconds;
    private int loopCounter;
    private double initialAngle;		//stores initial angle which is used as a reference for keeping the move straight
    
    public AutonomousArcadeDrivePiDistMinusConst(double encoderCount, double timeoutSeconds) {
    	m_encoderCount = encoderCount;
        m_timeoutSeconds = timeoutSeconds;
        requires(Robot.drives);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_encoderCount = (SmartDashboard.getNumber("piDistance", 77.5) - 20.0) * Robot.drives.encodersPerInch;
    	initialAngle = Robot.drives.GetGyroAngle();	//gets initial angle to measure error in angle
    	Robot.drives.zeroEncoders();				//zeros encoder count so actual count can be compared to targets
    	loopCounter = 0;							//reset loop counter to check if command timed out
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("AutoState", "DriveStraight");
    	// get angle error as a function of current angle (0 to 359) minus initial angle (0 to 359)
    	double currentError = Robot.drives.GetGyroAngle() - initialAngle; 
    	// -359 <= currentError <= 359
    	//convert error to the shortest possible error ie. if error greater than 180 travel error minus 180 in the opposite direction
    	if (currentError > 180)
    	{
    		currentError -= 360;
    	}
    	else if (currentError < -180)
    	{
    		currentError += 360;
    	}
    	
    	//error is used proportionally as a function of sin.  gain of 12 seemed to keep the robot driving pretty straight
    	// so zero error yields all throttle and no turn.  trig functions require angle in radians
    	currentError = Math.toRadians(currentError * 12);
    	if (currentError < 0)
    	{
    		//drives forward at half speed(actually voltage) while turning right
    		Robot.drives.teleopDrive(Math.abs(Math.sin(currentError)), -0.5);
    		//SmartDashboard.putNumber("resultAngle", -Math.sin(currentError));
    	}
    	else
    	{
    		//drives forward at half speed(actually voltage) while turning left
    		Robot.drives.teleopDrive(-Math.abs(Math.sin(currentError)), -0.5);
    		//SmartDashboard.putNumber("resultAngle", Math.sin(currentError));
    	}
    	loopCounter++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// returns true when either negative left encoder count(left side is inverted) is within 3 inches of target OR
    	// returns true when either right encoder count is within 3 inches of target OR
    	// loop counter has exceeded the timeout
    	// ideally this code should look at target versus position and reverse directions so overshoot can be handled
    	// and distance can be negative
    	return (
    			(((-1 * m_encoderCount) - Robot.drives.GetLeftPosition()) > (Robot.drives.encodersPerInch * -3)) ||
    			((m_encoderCount - Robot.drives.GetRightPosition()) < (Robot.drives.encodersPerInch * 3)) ||
    			(loopCounter >= m_timeoutSeconds / 0.02));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drives.StopVelocityControl();
    	SmartDashboard.putString("AutoState", "DoneDrivingStraight");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
