package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AcquisitionCommand extends Command {

	double speed;
	public AcquisitionCommand(double direction) {
        requires(Robot.acquisition);
        speed = direction;
	}

	@Override
	protected void execute() {
		Robot.acquisition.acquisitionForward(speed);
	}

	

	@Override
	protected boolean isFinished() {
		return false;
	}

	protected void end() {
        Robot.acquisition.acquisitionStop(); 
    }

}
