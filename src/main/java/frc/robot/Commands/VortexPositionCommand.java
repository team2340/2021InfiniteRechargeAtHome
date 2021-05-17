package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.utilities.RobotUtils;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VortexPositionCommand extends Command {
	double distance = 0;
	double desiredSpot = 0;
	double range = .05;
	Boolean vortexDone;
	double desiredVortex;
	
	public VortexPositionCommand(double howFar) {
        requires(Robot.vortex);
		distance = howFar;

	}

	@Override
	protected void initialize() {
		vortexDone = false;
		desiredSpot = RobotUtils.getVortexRotationsFromInches(distance);
		desiredVortex = desiredSpot + Robot.vortex.currentPositionVortex();
		Robot.vortex.goToDesiredPosition(desiredSpot);
	}

	@Override
	protected void execute() {
		double vortexPos = Robot.vortex.currentPositionVortex();
		if(vortexPos <= desiredVortex+range && vortexPos >= desiredVortex-range){
			vortexDone = true;
			System.out.println ("Vortex done");
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
