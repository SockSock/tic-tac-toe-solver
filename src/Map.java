import lejos.hardware.port.MotorPort;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.geometry.Line;
import lejos.robotics.geometry.Rectangle;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.navigation.DestinationUnreachableException;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;
import lejos.robotics.pathfinding.PathFinder;
import lejos.robotics.pathfinding.ShortestPathFinder;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.utility.Delay;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;

public class Map {

	final static float WHEEL_DIAMETER = 51; // The diameter (mm) of the wheels
	final static float AXLE_LENGTH = 44; // The distance (mm) your two driven wheels
	final static float ANGULAR_SPEED = 100; // How fast around corners (degrees/sec)
	final static float LINEAR_SPEED = 70; // How fast in a straight line (mm/sec)
	
	public static void main(String[] args) throws Exception {
		BaseRegulatedMotor mL = new EV3LargeRegulatedMotor(MotorPort.A);
		// Create a ”Wheel” with Diameter 51mm and offset 22mm left of centre.
		Wheel wLeft = WheeledChassis.modelWheel(mL, WHEEL_DIAMETER).offset(-AXLE_LENGTH / 2);
		BaseRegulatedMotor mR = new EV3LargeRegulatedMotor(MotorPort.B);
		// Create a ”Wheel” with Diameter 51mm and offset 22mm right of centre.
		Wheel wRight = WheeledChassis.modelWheel(mR, WHEEL_DIAMETER).offset(AXLE_LENGTH / 2);
		// Create a ”Chassis” with two wheels on it.
		Chassis chassis = new WheeledChassis((new Wheel[] {wRight, wLeft}),
		WheeledChassis.TYPE_DIFFERENTIAL);
		// Finally create a pilot which can drive using this chassis.
		MovePilot pilot = new MovePilot(chassis);
		PoseProvider poseProvider = new OdometryPoseProvider(pilot);
		Navigator navigator = new Navigator(pilot, poseProvider);
		
		Line[] lines = new Line[8];
		// Book
		lines[0] = new Line(250f, 300f, 250f, 800f);
		lines[1] = new Line(230f, 780f, 550f, 780f);
		lines[2] = new Line(520f, 800f, 520f, 280f);
		lines[3] = new Line(230f, 320f, 550f, 320f);
		
		// Cup
		lines[4] = new Line(550f, 200f, 550f, 430f);
		lines[5] = new Line(500f, 390f, 720f, 390f);
		lines[6] = new Line(690f, 430f, 690f, 200f);
		lines[7] = new Line(730f, 250f, 480f, 250f);
		
		Rectangle bounds = new Rectangle (0, 0, 1200, 900);
		LineMap myMap = new LineMap (lines, bounds);
		PathFinder pf = new ShortestPathFinder(myMap);
		Path route = pf.findRoute(new Pose(20, 15, 90), new Waypoint (700, 800));
		navigator.followPath(route);
		navigator.waitForStop();
		
		LCD.drawString(poseProvider.getPose().toString(), 0, 2);

		Button.ENTER.waitForPressAndRelease();
	}
}