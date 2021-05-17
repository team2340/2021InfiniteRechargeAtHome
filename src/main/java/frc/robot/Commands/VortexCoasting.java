package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.utilities.RobotUtils;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VortexCoasting extends Command {
	double desiredSpot = 0;
	double desiredVortex = 0;
	double range = .05;
	Boolean vortexDone;

    double speed;
	public VortexCoasting(double direction) {
        requires(Robot.vortex);
		speed = direction;
	}

	@Override
	protected void initialize() {
		System.out.println("initialize");
		desiredSpot = RobotUtils.getVortexRotationsFromInches(RobotUtils.vortexDiameter* Math.PI /3);
		desiredVortex = desiredSpot + Robot.vortex.currentPositionVortex();
		System.out.println(desiredVortex + " " + Robot.vortex.currentPositionVortex());
		vortexDone = false;
	}

	@Override
	protected void execute() {
		double vortexPos = Robot.vortex.currentPositionVortex();
		if(vortexPos <= desiredVortex+range && vortexPos >= desiredVortex-range){
			vortexDone = true;
			System.out.println ("Vortex done");
			Robot.vortex.vortexForward(-1);
			Robot.vortex.vortexStop();
		}
		else{
			speed = ((desiredVortex - Robot.vortex.currentPositionVortex()) / desiredSpot) * .1;
			System.out.println ("execute else");
			Robot.vortex.vortexForward(speed);
		}
	}

	

	@Override
	protected boolean isFinished() {
		if (vortexDone == true){
			System.out.println ("Is finished");
			Robot.vortex.vortexStop(); 
			return true;
		}
		return false;
    }

    protected void end() {
        Robot.vortex.vortexStop(); 
    }

}
