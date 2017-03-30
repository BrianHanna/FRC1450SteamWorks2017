package org.usfirst.frc1450.FRCSteamworks.commands.driveCommands;

import org.usfirst.frc1450.FRCSteamworks.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MiddleLiftCommandGroup extends CommandGroup {

    public MiddleLiftCommandGroup() {
    	addSequential(new CameraBasedTurn(10.0));
    	addSequential(new AutonomousArcadeDrivePiDistMinusConst((SmartDashboard.getNumber("piDistance", 77.5) - 10.0) * Robot.drives.encodersPerInch, 8.0));
    	addSequential(new CameraBasedTurn(10.0));
    	addSequential(new AutonomousArcadeDrivePiDist((SmartDashboard.getNumber("piDistance", 77.5)) * Robot.drives.encodersPerInch, 8.0));
    }
}
