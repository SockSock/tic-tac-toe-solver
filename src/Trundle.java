import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
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

public class Trundle implements Behavior {
	private EV3TouchSensor touchSensor = new EV3TouchSensor(SensorPort.S2);
	private SampleProvider touch = touchSensor.getTouchMode();
	private MovePilot pilot;
	private Map map;
	private TicTacToe ticTacToe;
	private PoseProvider poseProvider;
	private Navigator navigator;
	private boolean flag = false;
	private float[] samples = new float[1];
	private final float HALF_METRE_DISTANCE = 250;
	private final float TEN_CENTIMETRE_DISTANCE = 50;
	private final float FORTY_CENTIMETRE_DISTANCE = 175;
	private final float NINETY_DEGREE_TURN = 220;
	
	public Trundle(MovePilot pilot, Map map, PoseProvider poseProvider, Navigator navigator, TicTacToe ticTacToe) {
		this.pilot = pilot;
		this.map = map;
		this.poseProvider = poseProvider;
		this.navigator = navigator;
		this.ticTacToe = ticTacToe;
	}

	@Override
	public boolean takeControl() {
		touch.fetchSample(samples, 0);
		if (samples[0] > 0) {
			flag = false;
			return true;
		}
		
		return false;
	}

	@Override
	public void action() {
		if (flag) {
			
		} else {
			for (int i = 0; i < 1; i++) {
				pilot.travel(HALF_METRE_DISTANCE);
				pilot.rotate(NINETY_DEGREE_TURN);
				pilot.travel(TEN_CENTIMETRE_DISTANCE);
				pilot.rotate(NINETY_DEGREE_TURN);
				pilot.travel(HALF_METRE_DISTANCE);
				pilot.rotate(-NINETY_DEGREE_TURN);
				pilot.travel(TEN_CENTIMETRE_DISTANCE);
				pilot.rotate(-NINETY_DEGREE_TURN);
				pilot.travel(HALF_METRE_DISTANCE);
				pilot.rotate(-NINETY_DEGREE_TURN);
				pilot.travel(HALF_METRE_DISTANCE);
				pilot.rotate(-NINETY_DEGREE_TURN);
				pilot.travel(HALF_METRE_DISTANCE);
				pilot.rotate(-NINETY_DEGREE_TURN);
				pilot.travel(TEN_CENTIMETRE_DISTANCE);
				pilot.rotate(-NINETY_DEGREE_TURN);
				float[] destination = this.ticTacToe.getDestination();
				this.navigator.goTo(destination[0], destination[1]);
			}
		}
		
		flag = true;
	}

	@Override
	public void suppress() {
		
	}
	
	public boolean getFlag() {
		return flag;
	}
}