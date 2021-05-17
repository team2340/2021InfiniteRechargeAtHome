package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;

public class VortexSubsystem extends Subsystem{
    CANSparkMax vortex = null;
    public CANEncoder encoderVortex = null;
    public CANPIDController pidControllerVortex = null;
    private double kP = 6e-5; 
	private double kI = 0;
	private double kD = 0; 
	private double kIz = 0; 
	private double kFF = 0.000015; 
	private double kMaxOutput = 1; 
	private double kMinOutput = -1;

    public VortexSubsystem() {
        create();
    }

	private void create() {
		try {
            vortex = new CANSparkMax(OI.VORTEX_TAL_ID, MotorType.kBrushless);
            vortex.restoreFactoryDefaults();
            pidControllerVortex = vortex.getPIDController();
            encoderVortex = vortex.getEncoder();
            // PID coefficients
		kP = 0.1; 
		kI = 1e-4;
		kD = 1; 
		kIz = 0; 
		kFF = 0; 
		kMaxOutput = 1; 
		kMinOutput = -1;
	
		// set PID coefficients
		pidControllerVortex.setP(kP);
		pidControllerVortex.setI(kI);
		pidControllerVortex.setD(kD);
		pidControllerVortex.setIZone(kIz);
		pidControllerVortex.setFF(kFF);
		pidControllerVortex.setOutputRange(kMinOutput, kMaxOutput);
		} catch (Exception ex) {
			System.out.println("createVortex FAILED");
        } 
        vortex.setIdleMode(IdleMode.kBrake);   
    }

    public void goToDesiredPosition(double desiredPosition){
		pidControllerVortex.setReference(-desiredPosition + currentPositionVortex() , ControlType.kPosition);
	}

    public double currentPositionVortex(){
			return encoderVortex.getPosition();
	}
    
    public void vortexForward(double speed){
        vortex.set(speed);
    }

   
 
    public void vortexStop(){
        vortex.set(0);
    }


    @Override
    protected void initDefaultCommand() {
        //setDefaultCommand(new VortexCommand());
    }
}
