package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VortexCommand extends Command {

    double speed;
	public VortexCommand(double direction) {
        requires(Robot.vortex);
        speed = direction;
	}

	@Override
	protected void execute() {
		Robot.vortex.vortexForward(speed);
	}

	

	@Override
	protected boolean isFinished() {
		return false;
    }

    protected void end() {
        Robot.vortex.vortexStop(); 
    }

}
