
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;

public class PneumaticSubsystem extends Subsystem{
    private Solenoid festioSolenoid;
    private Solenoid festioSolenoid2;
    private Relay relay;
    private Compressor compressor;

    public PneumaticSubsystem() {
        festioSolenoid = new Solenoid(OI.PNEUMATICS_TAL_ID, OI.SOLENOID1_ID);
        festioSolenoid2 = new Solenoid(OI.PNEUMATICS_TAL_ID, OI.SOLENOID2_ID);
        relay = new Relay(0);
        compressor = new Compressor();
        compressor.setClosedLoopControl(true);
    }

    
    public void pneumaticOn(){
        festioSolenoid.set(false);
        festioSolenoid2.set(true);
        relay.set(Relay.Value.kForward);
    }

   
 
    public void pneumaticOff(){
        festioSolenoid.set(true);
        festioSolenoid2.set(false);
        relay.set(Relay.Value.kOff);
    }


    @Override
    protected void initDefaultCommand() {

    }
}
