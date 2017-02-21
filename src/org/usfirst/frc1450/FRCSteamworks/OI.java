// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1450.FRCSteamworks;

import org.usfirst.frc1450.FRCSteamworks.commands.*;
import org.usfirst.frc1450.FRCSteamworks.commands.climberCommands.ClimbCommand;
import org.usfirst.frc1450.FRCSteamworks.commands.climberCommands.DisableClimber;
import org.usfirst.frc1450.FRCSteamworks.commands.driveCommands.driveForwardEncoderCount;
import org.usfirst.frc1450.FRCSteamworks.commands.driveCommands.imageProcessingCenterTarget;
import org.usfirst.frc1450.FRCSteamworks.commands.driveCommands.turnLeft;
import org.usfirst.frc1450.FRCSteamworks.commands.driveCommands.turnRight;
import org.usfirst.frc1450.FRCSteamworks.commands.gearRelease.closeGearRelease;
import org.usfirst.frc1450.FRCSteamworks.commands.gearRelease.openGearRelease;
import org.usfirst.frc1450.FRCSteamworks.commands.gearShiftCommands.GearShiftLeft;
import org.usfirst.frc1450.FRCSteamworks.commands.gearShiftCommands.GearShiftRight;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import org.usfirst.frc1450.FRCSteamworks.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton joystickButton1;
    public Joystick driveJoystick;
    public JoystickButton joystickButton2;
    public JoystickButton joystickButton3;
    public Joystick copilotJoystick;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static int xBoxLeftX = 0;
	public static int xBoxLeftY = 1;
	public static int xBoxLeftTrigger = 2;
	public static int xBoxRightTrigger = 3;
	public static int xBoxRightX = 4;
	public static int xBoxRightY = 5;
	public static int xBoxAButton = 1;	//fixed
	public static int xBoxBButton = 2;	//
	public static int xBoxXButton = 3;	//fixed
	public static int xBoxYButton = 4;	//fixed
	public static int xBoxLeftButton = 5;
	public static int xBoxRightButton = 6;
	public static int xBoxBackButton = 7;
	public static int xBoxStartButton = 8;
	public static int xBoxLeftStickButton = 9;
	public static int xBoxRightStickButton = 10;
	
    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        copilotJoystick = new Joystick(1);
        
        joystickButton2 = new JoystickButton(copilotJoystick, 1);
        joystickButton2.whileHeld(new ClimbCommand());
        joystickButton2.whenReleased(new DisableClimber());
        joystickButton3.whenPressed(new openGearRelease());
        joystickButton3.whenReleased(new closeGearRelease());
        driveJoystick = new Joystick(0);
        
        joystickButton1 = new JoystickButton(driveJoystick, 1);
        joystickButton1.whileHeld(new AutonomousCommand());


        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("leftHookAutonomousCommandGroup", new leftHookAutonomousCommandGroup());
        SmartDashboard.putData("imageProcessingCenterTarget", new imageProcessingCenterTarget());
        SmartDashboard.putData("driveForwardEncoderCount: leftPreset", new driveForwardEncoderCount(500.0, 10.0));
        SmartDashboard.putData("driveForwardEncoderCount: centerPreset", new driveForwardEncoderCount(500.0, 10.0));
        SmartDashboard.putData("driveForwardEncoderCount: rightPreset", new driveForwardEncoderCount(500.0, 10.0));
        SmartDashboard.putData("driveForwardEncoderCount: leftPreset2", new driveForwardEncoderCount(250.0, 5.0));
        SmartDashboard.putData("driveForwardEncoderCount: rightPreset2", new driveForwardEncoderCount(250.0, 5.0));
        SmartDashboard.putData("driveForwardEncoderCount: leftPreset3", new driveForwardEncoderCount(120.0, 2.5));
        SmartDashboard.putData("driveForwardEncoderCount: rightPreset3", new driveForwardEncoderCount(120.0, 2.5));
        SmartDashboard.putData("openGearRelease", new openGearRelease());
        SmartDashboard.putData("closeGearRelease", new closeGearRelease());
        SmartDashboard.putData("turnRight: defaultTurn", new turnRight(45.0, 2));
        SmartDashboard.putData("turnLeft: default", new turnLeft(45.0, 2.0));
        SmartDashboard.putData("GearShiftLeft", new GearShiftLeft());
        SmartDashboard.putData("GearShiftRight", new GearShiftRight());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getdriveJoystick() {
        return driveJoystick;
    }
    
    public double getDriveJoystickYAxis(){
    	return driveJoystick.getRawAxis(xBoxLeftY);
    }
    
    public double getDriveJoystickXAxis(){
    	return driveJoystick.getRawAxis(xBoxLeftX);
    }

    public Joystick getcopilotJoystick() {
        return copilotJoystick;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}
