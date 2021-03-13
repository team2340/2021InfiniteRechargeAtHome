package frc.robot.Commands;

import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Rotation extends Command {
	long startTime = 0;
	double desiredAngle = 0;
	boolean rotateRight;
	double angle= 0;
	double turnAngle=0;

	public Rotation(double wantedAngle) {
		requires(Robot.drive);
		desiredAngle = wantedAngle;
	}

	@Override
	protected void initialize() {
		startTime = System.currentTimeMillis();
		// Robot.drive.setForSpeed();
		SmartDashboard.putNumber("Current angle: ", Robot.oi.gyro.getAngle());
		turnAngle = desiredAngle - Robot.oi.gyro.getAngle();
		Robot.oi.gyro.reset();
		if (turnAngle > 0) {
			rotateRight = true;
		}
		else {
			rotateRight = false;
		}
		turnAngle = Math.abs(turnAngle);
	}
	
	@Override
	protected void execute() {
		angle = Math.abs(Robot.oi.gyro.getAngle());
		SmartDashboard.putNumber("Gyro angle", angle);

		if (angle >= turnAngle) {
			Robot.drive.rotate(0);
		}
		else {
			double t = 1023*((turnAngle - angle)/turnAngle);
			if (t < 900) t = 900; //was 800
			double rotateSpeed = t;
			if (rotateRight) {
				Robot.drive.rotate(rotateSpeed);
			}
			else {
				Robot.drive.rotate(-rotateSpeed);
			}
		}
	}

	@Override
	protected boolean isFinished() {
		if (angle >= turnAngle) {
			Robot.drive.rotate(0);

			Robot.oi.gyro.reset();
			return true;
		}
		else {
			return false;
		}
	}
};