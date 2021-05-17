package frc.robot;

public class OI {
//magic numbers~~
	public static final int BUTTON_1 = 1;
	public static final int BUTTON_2 = 2; 
	public static final int BUTTON_3 = 3;
	public static final int BUTTON_4 = 4;
	public static final int BUTTON_5 = 5;
	public static final int BUTTON_6 = 6;
	public static final int BUTTON_7 = 7;
	public static final int BUTTON_8 = 8;

/*Controller Ports*/
	public static final int DRIVE_PORT = 1;
	public static final int ACQUISITION_PORT = 2;
	
/*CAN IDs*/
	//Wheel Ids
	public static final int BACK_RIGHT_TAL_ID = 55;
	public static final int BACK_LEFT_TAL_ID = 11;

	// Talon ids
	public static final int SHOOTING_RIGHT_TAL_ID = 5;
	public static final int SHOOTING_LEFT_TAL_ID = 9;
	public static final int ACQUISITION_TAL_ID = 7;
	public static final int UPTAKE_TAL_ID = 3;
	public static final int VORTEX_TAL_ID = 33;
	public static final int PNEUMATICS_TAL_ID = 1;
	public static final int SOLENOID_ID = 0;
	public static final int SOLENOID1_ID = 1;
	public static final int SOLENOID2_ID = 2;

	public final double CAM_VIEWING_ANGLE = 61.0;
	public final double IMG_WIDTH = 160.0;
	public final double IMG_HEIGHT = 120.0;
}
