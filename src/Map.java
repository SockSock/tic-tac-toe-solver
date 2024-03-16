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
import lejos.robotics.geometry.Point;

public class Map {
	private Navigator navigator;
	private Line[] lines;
	private Rectangle bounds;
	private LineMap myMap;
	private PathFinder pf;
	private Path route;
	
	public Map(Navigator navigator) {
		this.navigator = navigator;
		lines = new Line[4];
		bounds = new Rectangle (0, 0, 900, 900);
		
		// Lines = Bounds
		lines[0] = new Line(300f, 0f, 300f, 900f);
		lines[1] = new Line(600f, 0f, 600f, 900f);
		lines[2] = new Line(0f, 300f, 900f, 300f);
		lines[3] = new Line(0f, 600f, 900f, 600f);
		
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
		this.navigator.clearPath();
		
	}
	
	public Pose getPose() {
		return this.navigator.getPoseProvider().getPose();
	}
}