package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;

public class VortexSubsystem extends Subsystem{
    CANSparkMax vortex = null;

    public VortexSubsystem() {
        create();
    }

	private void create() {
		try {
            vortex = new CANSparkMax(OI.VORTEX_TAL_ID, MotorType.kBrushless);
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
