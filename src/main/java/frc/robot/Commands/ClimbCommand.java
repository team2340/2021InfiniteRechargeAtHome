package frc.robot.Commands;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class ClimbCommand extends Command {

    private Joystick controller;


    public ClimbCommand() {
        requires(Robot.climb);
        controller = Robot.oi.acquisitionController;

	}

	@Override
	protected void initialize() {
        
	}


	@Override
	protected void execute() {
        Robot.climb.talonTest();
	}

	@Override
	protected boolean isFinished() {
        return false;
	}	
}
