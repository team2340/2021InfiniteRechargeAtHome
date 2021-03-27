package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.commands.ArcadeDriveCommand;

import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveSubsystem extends Subsystem {

	DifferentialDrive robotDrive;
	public CANEncoder encoderOne = null;
	public CANEncoder encoderTwo = null;
	public CANSparkMax backLeft = null;
	public CANSparkMax backRight = null;
	public CANPIDController pidControllerLeftWheel = null;
	public CANPIDController pidControllerRightWheel = null;
	private double kP = 6e-5; 
	private double kI = 0;
	private double kD = 0; 
	private double kIz = 0; 
	private double kFF = 0.000015; 
	private double kMaxOutput = 1; 
	private double kMinOutput = -1;
	
	protected void initDefaultCommand() {
		setDefaultCommand(new ArcadeDriveCommand());
	}

	public DriveSubsystem() {
		//createLeftSide();
		//createRightSide();
//		setBrakeMode(true);
		// initialize motor
		backRight = new CANSparkMax(OI.BACK_RIGHT_TAL_ID, MotorType.kBrushless);
		backLeft = new CANSparkMax(OI.BACK_LEFT_TAL_ID, MotorType.kBrushless);
	
		/**
		 * The restoreFactoryDefaults method can be used to reset the configuration parameters
		 * in the SPARK MAX to their factory default state. If no argument is passed, these
		 * parameters will not persist between power cycles
		 */
		backLeft.restoreFactoryDefaults();
		backRight.restoreFactoryDefaults();
	
		/**
		 * In order to use PID functionality for a controller, a CANPIDController object
		 * is constructed by calling the getPIDController() method on an existing
		 * CANSparkMax object
		 */
		pidControllerLeftWheel = backLeft.getPIDController();
		pidControllerRightWheel = backRight.getPIDController();
	
		// Encoder object created to display position values
		encoderOne = backLeft.getEncoder();
		encoderTwo = backRight.getEncoder();
	
	
		// PID coefficients
		kP = 0.1; 
		kI = 1e-4;
		kD = 1; 
		kIz = 0; 
		kFF = 0; 
		kMaxOutput = 1; 
		kMinOutput = -1;
	
		// set PID coefficients
		pidControllerLeftWheel.setP(kP);
		pidControllerLeftWheel.setI(kI);
		pidControllerLeftWheel.setD(kD);
		pidControllerLeftWheel.setIZone(kIz);
		pidControllerLeftWheel.setFF(kFF);
		pidControllerLeftWheel.setOutputRange(kMinOutput, kMaxOutput);
	
		pidControllerRightWheel.setP(kP);
		pidControllerRightWheel.setI(kI);
		pidControllerRightWheel.setD(kD);
		pidControllerRightWheel.setIZone(kIz);
		pidControllerRightWheel.setFF(kFF);
		pidControllerRightWheel.setOutputRange(kMinOutput, kMaxOutput);
	
		// display PID coefficients on SmartDashboard
		SmartDashboard.putNumber("P Gain", kP);
		SmartDashboard.putNumber("I Gain", kI);
		SmartDashboard.putNumber("D Gain", kD);
		SmartDashboard.putNumber("I Zone", kIz);
		SmartDashboard.putNumber("Feed Forward", kFF);
		SmartDashboard.putNumber("Max Output", kMaxOutput);
		SmartDashboard.putNumber("Min Output", kMinOutput);
		SmartDashboard.putNumber("Set Rotations", 0);
	  
		robotDrive = new DifferentialDrive(backLeft, backRight);
		robotDrive.setSafetyEnabled(false);
	}

	public void setArcadeSpeed(double x, double y){
		robotDrive.arcadeDrive(-y, x);
	}
	public double currentPositionLeftWheel()
	{
			return encoderOne.getPosition();
	}

	public void goToDesiredPosition(double desiredPosition){
		pidControllerLeftWheel.setReference(-desiredPosition + currentPositionLeftWheel() , ControlType.kPosition);
		pidControllerRightWheel.setReference(desiredPosition + currentPositionRightWheel() , ControlType.kPosition);
	}

	public double currentPositionRightWheel()
	{
		return encoderTwo.getPosition();
	}

	public void rotate(double amt){
		backRight.set(-amt);
		backLeft.set(-amt);
	}
}
