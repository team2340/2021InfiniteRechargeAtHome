// /*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotUtils.AutoMode;
import frc.robot.Commands.AutoDriveForward;
import frc.robot.Commands.Rotation;
import frc.robot.subsystems.DriveSubsystem;

public class Robot extends TimedRobot {
  public static final OI oi = new OI();
  public static DriveSubsystem drive = null;
  //public static final DebugLogger myLogger = new DebugLogger();
  public static SendableChooser<Integer> judgesTargetColor = new SendableChooser<Integer>();
  SendableChooser<AutoMode> autoMode = new SendableChooser<AutoMode>();
  CommandGroup autonomousCommand = null;
  public static UsbCamera camera1;
  public static UsbCamera camera2;
  public static ADXRS450_Gyro gyro = null;
  public static Joystick driveController = new Joystick(OI.DRIVE_PORT);
	public static Joystick acquisitionController = new Joystick(OI.ACQUISITION_PORT);

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    // myLogger.open("logs/", "DebugLogger", ".csv");
    autoMode.setDefaultOption("Disabled", AutoMode.DISABLED);
    //autoMode.addOption("DriveForward", AutoMode.DriveForward);
    autoMode.addOption("Left_Cross_and_Dump", AutoMode.Left_Cross_and_Dump); // One option of starting point during Autonomous
		autoMode.addOption("Right_Cross_and_DiagonalDump", AutoMode.Right_Cross_and_DiagonalDump); // One option of starting point during Autonomous
		autoMode.addOption("Center_Cross_and_Dump", AutoMode.Center_Cross_and_Dump); // One option of starting point during Autonomous
    autoMode.addOption("Right_Cross_and_90degreesDump", AutoMode.Right_Cross_and_90degreesDump);// One option
    autoMode.addOption("CrossOnly", AutoMode.CrossOnly);// One option
    SmartDashboard.putData("Autonomous Modes", autoMode);

    judgesTargetColor.setDefaultOption("unknown", 0);
    judgesTargetColor.addOption("yellow", 1);
    judgesTargetColor.addOption("red", 2);
    judgesTargetColor.addOption("green", 3);
    judgesTargetColor.addOption("blue", 4);
    SmartDashboard.putData("judges' Target Color", judgesTargetColor);

    //camera1 = CameraServer.getInstance().startAutomaticCapture(0);
    //camera2 = CameraServer.getInstance().startAutomaticCapture(1);

    RobotUtils.lengthOfRobot(30.25);
    RobotUtils.widthOfRobot(37);
    RobotUtils.setWheelDiameter(4);
    gyro = new ADXRS450_Gyro();
    //TODO: Joy needs check: FRC says it creates an ADXRS450_Gyro object on the MXP SPI port
    //Gyro gyro = new ADXRS450_Gyro(/*SPI.Port.kMXP*/);
    drive = new DriveSubsystem();

        // Binds the ColorSensorPositionCommand to be scheduled when the button3 of the joystick is pressed
        //When button 3 is pressed again, the ColorSensorPositionCommand would stop.
    // JoystickButton driveButton3 = new JoystickButton(oi.driveController, RobotMap.BUTTON_3);

    // JoystickButton driveButton4 = new JoystickButton(oi.driveController, RobotMap.BUTTON_4);

    //JoystickButton acqButton5 = new JoystickButton(oi.acquisitionController, RobotMap.BUTTON_5);
    // driveButton3.whileHeld(new ClimbCommand());
/*
    JoystickButton acqButton3 = new JoystickButton(oi.acquisitionController, RobotMap.BUTTON_3);
    acqButton3.whileHeld(new AcquisitionReverseCommand());

    JoystickButton acqButton6 = new JoystickButton(oi.acquisitionController, RobotMap.BUTTON_6);
    acqButton6.whileHeld(new DumpingSlowCommand());

    JoystickButton acqButton4 = new JoystickButton (oi.acquisitionController, RobotMap.BUTTON_4);
    acqButton4.whileHeld(new DumpingReverseCommand());

    JoystickButton acqButton1 = new JoystickButton (oi.acquisitionController, RobotMap.BUTTON_1);
    acqButton1.whileHeld(new DumpingCommand());

    JoystickButton driveButton2 = new JoystickButton(oi.driveController, RobotMap.BUTTON_2);
    driveButton2.whenPressed(new CameraCommand());*/
  }

  @Override
  public void robotPeriodic() {

  }

  @Override
  public void autonomousInit() {
    AutoMode am = autoMode.getSelected();
    autonomousCommand = new CommandGroup();

    if(am == AutoMode.DISABLED) {
     } 
     if(am == AutoMode.Left_Cross_and_Dump){
       System.out.println("Left cross and dump");
     autonomousCommand.addSequential(new AutoDriveForward(12.5663706));
     //autonomousCommand.addSequential(new Rotation(90));
     //autonomousCommand.addSequential(new AutoDriveForward(15));
     }

     if(am == AutoMode.Center_Cross_and_Dump) {
       System.out.println("Center cross and dump");
      autonomousCommand.addSequential(new AutoDriveForward(16));
      autonomousCommand.addSequential(new Rotation(90));
      autonomousCommand.addSequential(new AutoDriveForward(16));
      autonomousCommand.addSequential(new Rotation(90));
      autonomousCommand.addSequential(new AutoDriveForward(16));
      autonomousCommand.addSequential(new Rotation(90));
      autonomousCommand.addSequential(new AutoDriveForward(16));
      autonomousCommand.addSequential(new Rotation(90));
     }

    if (autonomousCommand != null){
      autonomousCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    if(autonomousCommand!=null){
      autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();

  }
}
// package frc.robot;

// import edu.wpi.cscore.UsbCamera;
// import edu.wpi.first.wpilibj.ADXRS450_Gyro;
// import edu.wpi.first.wpilibj.TimedRobot;
// import edu.wpi.first.wpilibj.command.Scheduler;
// import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.Commands.AutoDriveForward;
// import frc.robot.RobotUtils.AutoMode;
// import frc.robot.subsystems.AcquisitionSubsystem;
// import frc.robot.subsystems.ClimbSubsystem;
// import frc.robot.subsystems.DriveSubsystem;
// import frc.robot.subsystems.DumpingSubsystem;

// import com.revrobotics.CANEncoder;
// import com.revrobotics.CANPIDController;
// import com.revrobotics.CANSparkMax;
// import com.revrobotics.ControlType;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// public class Robot extends TimedRobot {
//   public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
//   public static final OI oi = new OI();
//   public static DriveSubsystem drive = null;
//   public static AcquisitionSubsystem acquisition = null;
//   public static DumpingSubsystem dumping = null;
//   public static ClimbSubsystem climb = null;
//   public static UsbCamera camera1;
//   public static UsbCamera camera2;
//   double rotations = 0;
//   public static SendableChooser<Integer> judgesTargetColor = new SendableChooser<Integer>();
//   SendableChooser<AutoMode> autoMode = new SendableChooser<AutoMode>();

//   @Override
//   public void robotInit() {
//     drive = new DriveSubsystem();
//     oi.gyro = new ADXRS450_Gyro();
// }

//   @Override
//   public void teleopInit() {
//     // read PID coefficients from SmartDashboard
//     double p = SmartDashboard.getNumber("P Gain", 0);
//     double i = SmartDashboard.getNumber("I Gain", 0);
//     double d = SmartDashboard.getNumber("D Gain", 0);
//     double iz = SmartDashboard.getNumber("I Zone", 0);
//     double ff = SmartDashboard.getNumber("Feed Forward", 0);
//     double max = SmartDashboard.getNumber("Max Output", 0);
//     double min = SmartDashboard.getNumber("Min Output", 0);
//     rotations = SmartDashboard.getNumber("Set Rotations", 0);

//     // if PID coefficients on SmartDashboard have changed, write new values to controller
    

//     /**
//      * PIDController objects are commanded to a set point using the 
//      * SetReference() method.
//      * 
//      * The first parameter is the value of the set point, whose units vary
//      * depending on the control type set in the second parameter.
//      * 
//      * The second parameter is the control type can be set to one of four 
//      * parameters:
//      *  com.revrobotics.ControlType.kDutyCycle
//      *  com.revrobotics.ControlType.kPosition
//      *  com.revrobotics.ControlType.kVelocity
//      *  com.revrobotics.ControlType.kVoltage
//      */
//     //drive.pidControllerLeftWheel.setReference(RobotUtils.getRotationsFromInches(rotations), ControlType.kPosition);
//     //drive.pidControllerRightWheel.setReference(RobotUtils.getRotationsFromInches(rotations), ControlType.kPosition);
//     //drive.goToDesiredPosition(RobotUtils.getRotationsFromInches(rotations));
//     (new AutoDriveForward(rotations)).start();
//   }

//   public void teleopPeriodic(){
//     Scheduler.getInstance().run();

//     SmartDashboard.putNumber("SetPoint", RobotUtils.getRotationsFromInches(rotations));
//     SmartDashboard.putNumber("ProcessVariableLeft", drive.encoderOne.getPosition());
//     SmartDashboard.putNumber("ProcessVariableRight", drive.encoderTwo.getPosition());

//   }
// }