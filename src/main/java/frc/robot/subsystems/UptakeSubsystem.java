package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;

public class UptakeSubsystem extends Subsystem {
    WPI_TalonSRX uptake = null;

    private UptakeSubsystem() {
        create();
	}

	private void create() {
		try {
			uptake = new WPI_TalonSRX(OI.UPTAKE_TAL_ID);//change to new talon
			uptake.setSensorPhase(true);
		} catch (Exception ex) {
			System.out.println("createUptake FAILED");
		}
    }

    
    public void uptakeForward(double speed){
        uptake.set(speed);
    }

   
 
    public void uptakeStop(){
        uptake.set(0);
    }


    @Override
    protected void initDefaultCommand() {
        //setDefaultCommand(new DumpingCommand());
    }
}