package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PneumaticCommand extends Command {

    Boolean arms = false;
	public PneumaticCommand(Boolean on) {
        requires(Robot.pneumatic);
        arms = on;
	}

	@Override
	protected void initialize() {
        if(arms == true){
            Robot.pneumatic.pneumaticOn();
        }
        else {
            Robot.pneumatic.pneumaticOff();
        }
	}

	

	@Override
	protected boolean isFinished() {
		return true;
	}

}
