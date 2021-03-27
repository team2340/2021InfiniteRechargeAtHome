package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;

public class ShootingSubsystem extends Subsystem {
    WPI_TalonSRX shooting = null;

    private ShootingSubsystem() {
        create();
	}

	private void create() {
		try {
			shooting = new WPI_TalonSRX(OI.SHOOTING_TAL_ID);//change to new talon
			shooting.setSensorPhase(true);
		} catch (Exception ex) {
			System.out.println("createDumping FAILED");
		}
    }

    
    public void shootingForward(double speed){
        shooting.set(speed);
    }

   
 
    public void shootingStop(){
        shooting.set(0);
    }


    @Override
    protected void initDefaultCommand() {
        //setDefaultCommand(new DumpingCommand());
    }
}