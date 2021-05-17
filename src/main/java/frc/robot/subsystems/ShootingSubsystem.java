package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;

public class ShootingSubsystem extends Subsystem {
    WPI_TalonSRX shootingRight = null;
    WPI_TalonSRX shootingLeft = null;

    public ShootingSubsystem() {
        create();
	}

	private void create() {
		try {
            shootingRight = new WPI_TalonSRX(OI.SHOOTING_RIGHT_TAL_ID);
            shootingRight.configPeakOutputForward(100);
            shootingRight.configPeakOutputReverse(-100);
            shootingLeft = new WPI_TalonSRX(OI.SHOOTING_LEFT_TAL_ID);
            shootingLeft.configPeakOutputReverse(-100);
            shootingLeft.configPeakOutputForward(100);
		} catch (Exception ex) {
			System.out.println("createDumping FAILED");
		}
    }

    
    public void shootingForward(double speed){
        shootingRight.set(speed);
        shootingLeft.set(-speed);
    }

   
 
    public void shootingStop(){
        shootingRight.set(0);
        shootingLeft.set(0);
    }


    @Override
    protected void initDefaultCommand() {
        //setDefaultCommand(new DumpingCommand());
    }
}