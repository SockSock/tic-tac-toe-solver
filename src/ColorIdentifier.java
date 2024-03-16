import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTColorSensor;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.utility.Delay;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;
import lejos.robotics.pathfinding.PathFinder;
import lejos.robotics.pathfinding.ShortestPathFinder;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.Navigator;
import lejos.hardware.sensor.EV3ColorSensor;

public class ColorIdentifier implements Behavior {
	float[] level = new float[3];
	private SampleProvider color;
	private PoseProvider poseProvider;
	private TicTacToe ticTacToe;
	private float red;
	private float green;
	private float blue;
	
	public ColorIdentifier(SampleProvider color, PoseProvider poseProvider, TicTacToe ticTacToe) {
		this.color = color;
		this.poseProvider = poseProvider;
		this.ticTacToe = ticTacToe;
	}
	
	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		this.color.fetchSample(level, 0);

		red = level[0];
		green = level[1];
		blue = level[2];
		
		System.out.println(red + " " + green + " " + blue);
		
		if (red > 0.03 && red < 0.13 && green > 0.02 && green < 0.12 && blue > 0.03 && blue < 0.13) {
			// System.out.println("White");
		} else if (Math.max(red, Math.max(green, blue)) == red) {
			System.out.println("Player");
			this.ticTacToe.start(calculatePosition(this.poseProvider.getPose().getX(), this.poseProvider.getPose().getY()));
			Delay.msDelay(2000);
		} else if (Math.max(red, Math.max(green, blue)) == green) {
			System.out.println("Green");
		} else if (Math.max(red, Math.max(green, blue)) == blue) {
//			System.out.println("Computer");
//			this.ticTacToe.start(calculatePosition(this.poseProvider.getPose().getX(), this.poseProvider.getPose().getY()));
		} else {
			float intensity = red + green + blue;
			if (intensity < 0.5 && (red < 0.15 || green < 0.15 || blue < 0.15)) {
				System.out.println("Black");
			} else {
				System.out.println("Unknown");
			}
		}
	}

	@Override
	public void suppress() {

	}
	
	private float[] calculatePosition(float x, float y) {
		float[] coords = new float[2];
		
		if (x > 0 && x < 300 && y > 0 && y < 300) {
			coords[0] = 2;
			coords[1] = 0;
			return coords;
		} else if (x > 300 && x < 600 && y > 0 && y < 300) {
			coords[0] = 2;
			coords[1] = 1;
			return coords;
		} else if (x > 600 && x < 900 && y > 0 && y < 300) {
			coords[0] = 2;
			coords[1] = 2;
			return coords;
		} else if (x > 0 && x < 300 && y > 300 && y < 600) {
			coords[0] = 1;
			coords[1] = 0;
			return coords;
		} else if (x > 300 && x < 600 && y > 300 && y < 600) {
			coords[0] = 1;
			coords[1] = 1;
			return coords;
		} else if (x > 600 && x < 900 && y > 300 && y < 600) {
			coords[0] = 1;
			coords[1] = 2;
			return coords;
		} else if (x > 0 && x < 300 && y > 600 && y < 900) {
			coords[0] = 0;
			coords[1] = 0;
			return coords;
		} else if (x > 300 && x < 600 && y > 600 && y < 900) {
			coords[0] = 0;
			coords[1] = 1;
			return coords;
		} else if (x > 600 && x < 900 && y > 600 && y < 900) {
			coords[0] = 0;
			coords[1] = 2;
			return coords;
		}
		
		return coords;
	}
}