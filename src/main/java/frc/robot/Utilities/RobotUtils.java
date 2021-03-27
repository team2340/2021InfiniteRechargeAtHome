package frc.robot.Utilities;

public class RobotUtils {
	private static double wheelDiameter = 4;
	private static double lengthOfRobot = 33;
	private static double widthOfRobot = 28;

	public enum AutoMode {
		DISABLED,
		Right_Cross_and_DiagonalDump,
		Center_Cross_and_Dump,
		Left_Cross_and_Dump,
		Right_Cross_and_90degreesDump,
		CrossOnly
	}

	public static void lengthOfRobot(double _lengthOfRobot) {
		lengthOfRobot = _lengthOfRobot;
	}

	public static void widthOfRobot(double _widthOfRobot) {
		widthOfRobot = _widthOfRobot;
	}

	public static double getLengthOfRobot() {
		return lengthOfRobot;
	}

	public static double getWidthOfRobot() {
		return widthOfRobot;
	}

	public static void setWheelDiameter(double _wheelDiameter) {
		wheelDiameter = _wheelDiameter;
	}

	public static double getEncPositionFromIN(double distanceInInches) {
		return (distanceInInches / (wheelDiameter * Math.PI)) * 470;
	}

	public static double getRotationsFromInches(double inches) {
		return inches / (Math.PI * wheelDiameter);
	}
}
