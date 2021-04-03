package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;

public class VortexSubsystem extends Subsystem{
    WPI_TalonSRX vortex = null;

    public VortexSubsystem() {
        create();
    }

	private void create() {
		try {
			vortex = new WPI_TalonSRX(OI.VORTEX_TAL_ID);//change to new talon
			vortex.setSensorPhase(true);
		} catch (Exception ex) {
			System.out.println("createVortex FAILED");
		}
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
