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

public class Driver {
	final static float WHEEL_DIAMETER = 51; // The diameter (mm) of the wheels
	final static float AXLE_LENGTH = 44; // The distance (mm) your two driven wheels
	final static double ANGULAR_SPEED = 100; // How fast around corners (degrees/sec)
	final static double LINEAR_SPEED = 80; // How fast in a straight line (mm/sec)
	
	public static void main(String[] args) {
		BaseRegulatedMotor mL = new EV3LargeRegulatedMotor(MotorPort.A);
		Wheel wLeft = WheeledChassis.modelWheel(mL, WHEEL_DIAMETER).offset(-AXLE_LENGTH / 2);
		BaseRegulatedMotor mR = new EV3LargeRegulatedMotor(MotorPort.B);
		Wheel wRight = WheeledChassis.modelWheel(mR, WHEEL_DIAMETER).offset(AXLE_LENGTH / 2);
		Chassis chassis = new WheeledChassis((new Wheel[] {wRight, wLeft}),
		WheeledChassis.TYPE_DIFFERENTIAL);
		MovePilot pilot = new MovePilot(chassis);
		pilot.setLinearSpeed(LINEAR_SPEED);
		pilot.setAngularSpeed(ANGULAR_SPEED);
		PoseProvider poseProvider = new OdometryPoseProvider(pilot);
		Navigator navigator = new Navigator(pilot, poseProvider);
		
		EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S1);
		SampleProvider color = sensor.getRGBMode();
		WelcomeScreen welcomeScreen = new WelcomeScreen();
		final Behavior lowBattery = new LowBattery();
		final Behavior emergencyStop = new EmergencyStop();
		final TicTacToe ticTacToe = new TicTacToe();
		final Map map = new Map(navigator);
		final Trundle trundle = new Trundle(pilot, map, poseProvider, navigator, ticTacToe);
		final Behavior colorIdentifier = new ColorIdentifier(color, poseProvider, ticTacToe, trundle);
		
		welcomeScreen.displayWelcomeScreen("Jonathan", "Anish", "Arjun", "Version 32");
		welcomeScreen.waitForButtonPress();
		
		new Thread(new Runnable() {
			public void run() {
				Arbitrator ab = new Arbitrator(new Behavior[] {trundle, lowBattery});
				ab.go();
			}
		}).start();
		
		new Thread(new Runnable() {
			public void run() {
				Arbitrator ab = new Arbitrator(new Behavior[] {colorIdentifier});
				ab.go();
			}
		}).start();
		
		new Thread(new Runnable() {
			public void run() {
				Arbitrator ab = new Arbitrator(new Behavior[] {emergencyStop});
				ab.go();
			}
		}).start();
	}
}