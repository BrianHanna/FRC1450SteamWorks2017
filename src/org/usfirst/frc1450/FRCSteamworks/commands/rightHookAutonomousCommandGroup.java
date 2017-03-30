// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1450.FRCSteamworks.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc1450.FRCSteamworks.Robot;
import org.usfirst.frc1450.FRCSteamworks.commands.driveCommands.AutonomousArcadeDrive;
import org.usfirst.frc1450.FRCSteamworks.commands.driveCommands.CameraBasedDrive;
import org.usfirst.frc1450.FRCSteamworks.commands.driveCommands.CameraBasedTurn;
import org.usfirst.frc1450.FRCSteamworks.commands.driveCommands.Turn;

/**
 *
 */
public class rightHookAutonomousCommandGroup extends CommandGroup {


	//This code was designed for the right lift.  It should drive forward a fixed distance based on field geometries,
	//turn left 60 degrees and then drive forward a separate fixed distance based on field geometries. 
    public rightHookAutonomousCommandGroup() {

        addSequential(new AutonomousArcadeDrive((95.0) * Robot.drives.encodersPerInch,8));
        addSequential(new Turn(-60.0, 3.0));
        //addSequential(new CameraBasedDrive(6.0));
        //addSequential(new CameraBasedTurn(6.0));
        addSequential(new AutonomousArcadeDrive((35.0) * Robot.drives.encodersPerInch, 4.0));
 
    } 
}
