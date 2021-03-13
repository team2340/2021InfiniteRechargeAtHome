package frc.robot.Commands;

import frc.robot.Robot;
import frc.robot.RobotUtils;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoDriveForward extends Command {
	long startTime = 0;
	double distance = 0;
	double desiredSpot = 0;
	double range = .05;
	Boolean leftDone, rightDone;
	double desiredLeft, desiredRight; 



	public AutoDriveForward(double howFar /*unit: inches*/ ) {
		System.out.println("distance " + distance);
		requires(Robot.drive);
		distance = howFar/*-(.5*RobotUtils.getLengthOfRobot())*/;
		SmartDashboard.putNumber("distance ", distance);
		desiredSpot = distance;

		
	}

	@Override
	protected void initialize() {
		leftDone = false;
		rightDone = false;
		System.out.println("Initialize");
		Robot.oi.gyro.reset();
		startTime = System.currentTimeMillis();
//		double /*go = RobotUtils.distanceMinusRobot*/(distance);
//		Robot.drive.move(desiredSpot);
		desiredSpot = RobotUtils.getRotationsFromInches(distance);
		desiredLeft = -desiredSpot + Robot.drive.currentPositionLeftWheel();
		desiredRight = desiredSpot + Robot.drive.currentPositionRightWheel();
		Robot.drive.goToDesiredPosition(desiredSpot);
		SmartDashboard.putNumber("Dersired position", desiredSpot);
		SmartDashboard.putNumber("Current position left wheel", Robot.drive.currentPositionLeftWheel());
		SmartDashboard.putNumber("Current position right wheel", Robot.drive.currentPositionRightWheel());
		SmartDashboard.putNumber("Set reference left", desiredLeft);
		SmartDashboard.putNumber("Set reference right", desiredRight);
		

	}

	@Override
	protected void execute() {
		double leftPos = Robot.drive.currentPositionLeftWheel();
		double rightPos = Robot.drive.currentPositionRightWheel();
		//		int leftErr = Math.abs(Robot.oi.left.getClosedLoopError(0));
		//		int rightErr = Math.abs(Robot.oi.right.getClosedLoopError(0));

		//double range = 50; //Might be wrong, trying to fix drive
		SmartDashboard.putNumber("Current angle: ", Robot.oi.gyro.getAngle());
		SmartDashboard.putNumber("left position", Robot.drive.currentPositionLeftWheel());
		SmartDashboard.putNumber("right position ",Robot.drive.currentPositionRightWheel());
		
		//DO THAT HERE@!
		if(leftPos <= desiredLeft+range && leftPos >= desiredLeft-range){
			leftDone = true;
			System.out.println ("LeftSide done");
		}
		if (rightPos <= desiredRight+range && rightPos >= desiredRight-range){
			rightDone = true;
			System.out.println ("Rightside done");
		}	
	}


	@Override
	protected boolean isFinished() {
		if (rightDone == true && leftDone == true){
			System.out.println ("Is finished");
			Robot.drive.rotate(0);
			return true;
		}
		return false;
	}
}
