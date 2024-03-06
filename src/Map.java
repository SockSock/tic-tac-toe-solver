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
	private Navigator navigator;
	private Line[] lines;
	private Rectangle bounds;
	private LineMap myMap;
	private PathFinder pf;
	private Path route;
	
	public Map(Navigator navigator) {
		this.navigator = navigator;
		lines = new Line[8];
		bounds = new Rectangle (0, 0, 1200, 900);
		
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
		
		myMap = new LineMap (lines, bounds);
		pf = new ShortestPathFinder(myMap);
		try {
			route  = pf.findRoute(new Pose(20, 15, 90), new Waypoint (0, 0));
		} catch (DestinationUnreachableException e) {
			System.out.println("Bro I can't lie, the destination is unreachable.");
		}
	}
	
	public void move() {
		this.navigator.followPath(route);
		this.navigator.waitForStop();
		
	}
	
	public Pose getPose() {
		return poseProvider.getPose();
	}
}