package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UptakeShootCommand extends Command {
	long startTime = 0;
	public UptakeShootCommand() {
        requires(Robot.uptake);
        requires(Robot.shooting);
	}

	@Override
	protected void initialize() {
		startTime = System.currentTimeMillis();
	}
	@Override
	protected void execute() {
		
		Robot.shooting.shootingForward(1);
		
		if(startTime <= System.currentTimeMillis() - 3000) {
			Robot.uptake.uptakeForward(1);
		}

	} 

	
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
        Robot.shooting.shootingStop();
        Robot.uptake.uptakeStop();
	}

}