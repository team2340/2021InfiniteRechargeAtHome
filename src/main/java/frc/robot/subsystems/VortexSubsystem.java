package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class VortexSubsystem extends Subsystem{
    static private VortexSubsystem subsystem;

    private VortexSubsystem() {
        create();
    }

    public static VortexSubsystem getInstance() {
		if (subsystem == null) {
			subsystem = new VortexSubsystem();
		}
		return subsystem;
    }

	private void create() {
		try {
			Robot.oi.vortex = new WPI_TalonSRX(RobotMap.VORTEX_TAL_ID);//change to new talon
			Robot.oi.vortex.setSensorPhase(true);
		} catch (Exception ex) {
			System.out.println("createVortex FAILED");
		}
    }

    
    public void vortexForward(double speed){
        Robot.oi.vortex.set(speed);
    }

   
 
    public void vortexStop(){
        Robot.oi.vortex.set(0);
    }


    @Override
    protected void initDefaultCommand() {
        //setDefaultCommand(new VortexCommand());
    }
}
