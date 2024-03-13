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
	private float red;
	private float green;
	private float blue;
	
	public ColorIdentifier(SampleProvider color, PoseProvider poseProvider) {
		this.color = color;
		this.poseProvider = poseProvider;
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
		
//		System.out.println(red + " " + green + " " + blue);
		
		if (red > 0.11 && red < 0.14 && green > 0.09 && green < 0.125 && blue > 0.12 && blue < 0.15) {
			System.out.println("White");
		} else if (Math.max(red, Math.max(green, blue)) == red) {
			System.out.println("Red = " + this.poseProvider.getPose());
		} else if (Math.max(red, Math.max(green, blue)) == green) {
			System.out.println("Green");
		} else if (Math.max(red, Math.max(green, blue)) == blue) {
			System.out.println("Blue");
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
}