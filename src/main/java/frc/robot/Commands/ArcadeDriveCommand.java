package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArcadeDriveCommand extends Command {
	double x1=0;
    double x2=0;
	boolean buttonPressed = false;
	boolean buttonMode = false;

	public ArcadeDriveCommand() {
		requires(Robot.drive);
	}

	@Override
	protected void execute() {
		SmartDashboard.putNumber("left position", Robot.drive.currentPositionLeftWheel());
		SmartDashboard.putNumber("right position", Robot.drive.currentPositionRightWheel());

		double x, y, z;
		z = (3 - Robot.driveController.getThrottle()) / 2;
		y = Robot.driveController.getY() / z;
		x = Robot.driveController.getX() / z;

		Robot.drive.setArcadeSpeed(x, y);
	}

	

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}
