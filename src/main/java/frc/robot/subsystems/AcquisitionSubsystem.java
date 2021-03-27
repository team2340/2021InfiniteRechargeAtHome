package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;

public class AcquisitionSubsystem extends Subsystem {
    WPI_TalonSRX acquisition = null;

    private AcquisitionSubsystem() {
        create();
	}

	private void create() {
		try {
			acquisition = new WPI_TalonSRX(OI.ACQUISITION_TAL_ID);// change to new talon
			// Robot.oi.frontLeft.setSensorPhase(true);
		} catch (Exception ex) {
			System.out.println("createAcquisition FAILED");
		}
    }

    
    
    public void acquisitionForward(double speed){
        acquisition.set(-speed);
    }

    public void acquisitionReverse(){
        acquisition.set(.5);
    }
    
    public void acquisitionStop(){
        acquisition.set(0);
    }


    @Override
    protected void initDefaultCommand() {
       // setDefaultCommand(new AcquisitionCommand());
    }
}