package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class UptakeSubsystem extends Subsystem {
    static private UptakeSubsystem subsystem;

    private UptakeSubsystem() {
        create();
	}

    public static UptakeSubsystem getInstance() {
		if (subsystem == null) {
			subsystem = new UptakeSubsystem();
		}
		return subsystem;
    }

	private void create() {
		try {
			Robot.oi.uptake = new WPI_TalonSRX(RobotMap.UPTAKE_TAL_ID);//change to new talon
			Robot.oi.uptake.setSensorPhase(true);
		} catch (Exception ex) {
			System.out.println("createUptake FAILED");
		}
    }

    
    public void uptakeForward(double speed){
        Robot.oi.uptake.set(speed);
    }

   
 
    public void uptakeStop(){
        Robot.oi.uptake.set(0);
    }


    @Override
    protected void initDefaultCommand() {
        //setDefaultCommand(new DumpingCommand());
    }
}