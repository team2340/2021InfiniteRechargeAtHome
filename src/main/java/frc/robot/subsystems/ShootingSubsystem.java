package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ShootingSubsystem extends Subsystem {
    static private ShootingSubsystem subsystem;

    private ShootingSubsystem() {
        create();
	}

    public static ShootingSubsystem getInstance() {
		if (subsystem == null) {
			subsystem = new ShootingSubsystem();
		}
		return subsystem;
    }

	private void create() {
		try {
			Robot.oi.shooting = new WPI_TalonSRX(RobotMap.SHOOTING_TAL_ID);//change to new talon
			Robot.oi.shooting.setSensorPhase(true);
		} catch (Exception ex) {
			System.out.println("createDumping FAILED");
		}
    }

    
    public void shootingForward(double speed){
        Robot.oi.shooting.set(speed);
    }

   
 
    public void shootingStop(){
        Robot.oi.shooting.set(0);
    }


    @Override
    protected void initDefaultCommand() {
        //setDefaultCommand(new DumpingCommand());
    }
}