package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Commands.ClimbCommand;

public class ClimbSubsystem extends Subsystem {
    static private ClimbSubsystem subsystem;

    //controls the smoothness of the motion. We will change them while testing.
    public float positionPeakOutputVoltage = 10.0f/12.0f;
	public double vBusPeakOutputVoltage = 1f; //the peak output (between 0 and 1)


    public ClimbSubsystem() {
        create();
	}

	private void create() {
		try {
            Robot.oi.climb1 = new WPI_TalonSRX(RobotMap.CLIMB1_TAL_ID);
			Robot.oi.climb1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,0);
		    Robot.oi.climb1.selectProfileSlot(0,0); 
			Robot.oi.climb1.setSensorPhase(true); //TODO: find out which way the motor should turn

			Robot.oi.climb2 = new WPI_TalonSRX(RobotMap.CLIMB2_TAL_ID);
			Robot.oi.climb2.set(ControlMode.Follower, RobotMap.CLIMB1_TAL_ID);
			// Robot.oi.left.setSensorPhase(true);
		} catch (Exception ex) {
			System.out.println("createClimb FAILED");
		}
    }
    
    public void vBus_move(double amt) { //vBus mode controlling the speed of the motor from -1 to 1.
		setForVBus(); //to make sure in vBus mode every time.
		Robot.oi.climb1.set(amt);
	}

   
    @Override
    protected void initDefaultCommand() {
       setDefaultCommand(new ClimbCommand());
    }

	public void talonTest() {
		Robot.oi.climb1.set(1);
		Robot.oi.climb2.set(1);
	}

    //Encoders' codes: (not used now)
    public void setForPosition() {
		Robot.oi.climb1.set(ControlMode.Position, 0);
	    Robot.oi.climb1.selectProfileSlot(0,0);
        //setting up max and min voltage (which turns into speed) from -12 to +12.
	    Robot.oi.climb1.configPeakOutputForward(positionPeakOutputVoltage,0);
	    Robot.oi.climb1.configPeakOutputReverse(-positionPeakOutputVoltage,0);
	
	    Robot.oi.climb1.setSelectedSensorPosition(0,0,0);
	}
	
	public void setForVBus() {
		Robot.oi.climb1.set(ControlMode.PercentOutput,0);
        Robot.oi.climb1.configPeakOutputForward(vBusPeakOutputVoltage,0); 
	    Robot.oi.climb1.configPeakOutputReverse(-vBusPeakOutputVoltage,0);
	}
    
	public void PID_move(double amt) { //double amt = encoder counts.
		//this MOVE function: Using encoder counts to control the motor.
		//move to this position
		setForPosition(); //any time you want to call "move" function, it automatically calls PID.
		Robot.oi.climb1.set(ControlMode.Position, amt + Robot.oi.climb1.getSelectedSensorPosition(0));
		

	}
}
