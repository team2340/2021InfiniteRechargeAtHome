
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;

public class PneumaticSubsystem extends Subsystem{
    private Solenoid festioSolenoid;

    public PneumaticSubsystem() {
        festioSolenoid = new Solenoid(OI.PNEUMATICS_TAL_ID, OI.SOLENOID_ID);

    }

    
    public void pneumaticOn(){
        festioSolenoid.set(true);
    }

   
 
    public void pneumaticOff(){
        festioSolenoid.set(false);
    }


    @Override
    protected void initDefaultCommand() {
        //setDefaultCommand(new VortexCommand());
    }
}
