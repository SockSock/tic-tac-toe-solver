import lejos.robotics.subsumption.Arbitrator
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
import java.util.concurrent.*;

public class Trundle implements Behavior { // 40 secs
	private EV3TouchSensor touchSensor = new EV3TouchSensor(SensorPort.S2);
	private SampleProvider touch = touchSensor.getTouchMode();
	private MovePilot pilot;
	private TicTacToe ticTacToe;
	private boolean flag = false;
	private boolean goBack;
	private long startTime = 0;
	private String retreat;
	private float[] samples = new float[1];
	private final float THREE_BOX_DISTANCE = 380; // 380
	private final float ONE_BOX_DISTANCE = 85; // 380
	private final float TEN_CENTIMETRE_DISTANCE = 40; // 40
	private final float TWENTY_CENTIMETRE_DISTANCE = 60; // 60
	private final float NINETY_DEGREE_TURN = 200; // 200
	
	public Trundle(MovePilot pilot, TicTacToe ticTacToe) {
		this.pilot = pilot;
		this.ticTacToe = ticTacToe;
		this.retreat = "";
		this.goBack = false;
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
			for (int i = 0; i < 1; i++) { // The most useful for loop in the entire world (based based).
				startTime = System.currentTimeMillis();
				
				pilot.travel(THREE_BOX_DISTANCE);
				pilot.rotate(-NINETY_DEGREE_TURN);
				pilot.travel(TEN_CENTIMETRE_DISTANCE);
				pilot.rotate(-NINETY_DEGREE_TURN);
				pilot.travel(THREE_BOX_DISTANCE);
				pilot.rotate(NINETY_DEGREE_TURN);
				pilot.travel(TWENTY_CENTIMETRE_DISTANCE);
				pilot.rotate(NINETY_DEGREE_TURN);
				pilot.travel(THREE_BOX_DISTANCE);
				pilot.rotate(NINETY_DEGREE_TURN);
				pilot.travel(TEN_CENTIMETRE_DISTANCE);
				pilot.travel(TEN_CENTIMETRE_DISTANCE);
				pilot.rotate(NINETY_DEGREE_TURN);
				pilot.travel(THREE_BOX_DISTANCE);
				pilot.rotate(-NINETY_DEGREE_TURN);
				pilot.rotate(-NINETY_DEGREE_TURN);
			}
			
			this.retreat = this.ticTacToe.getRetreat();
			if(this.goBack) {
				if(this.retreat.equals("boxOne")) {
					getBoxOne();
				}else if (this.retreat.equals("boxTwo")) {
					getBoxTwo();
				}else if (this.retreat.equals("boxThree")) {
					getBoxThree();
				}else if (this.retreat.equals("boxFour")) {
					getBoxFour();
				}else if (this.retreat.equals("boxFive")) {
					getBoxFive();
				}else if (this.retreat.equals("boxSix")) {
					getBoxSix();
				}else if (this.retreat.equals("boxSeven")) {
					getBoxSeven();
				}else if (this.retreat.equals("boxEight")) {
					getBoxEight();
				}else if (this.retreat.equals("boxNine")) {
					getBoxNine();
				}
				
			}
			
			
			this.retreat = "";
			flag = true;
		}
	}

	@Override
	public void suppress() {
		
	}
	
	public boolean getFlag() {
		return flag;
	}
	
	public long getStartTime() {
		return startTime;
	}
	
	public void setGoBack(boolean goBack) {
		this.goBack = goBack;
	}
	
	public void getBoxOne() {
		pilot.travel(ONE_BOX_DISTANCE);
	}
	
	public void getBoxTwo() {
		pilot.travel(ONE_BOX_DISTANCE);
		pilot.travel(ONE_BOX_DISTANCE);
	}
	
	public void getBoxThree() {
		pilot.travel(ONE_BOX_DISTANCE);
		pilot.travel(ONE_BOX_DISTANCE);
		pilot.travel(ONE_BOX_DISTANCE);
	}
	public void getBoxFour() {
		pilot.travel(THREE_BOX_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(TEN_CENTIMETRE_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(TEN_CENTIMETRE_DISTANCE);
	}
	
	public void getBoxFive() {
		pilot.travel(THREE_BOX_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(TEN_CENTIMETRE_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(ONE_BOX_DISTANCE);
		pilot.travel(ONE_BOX_DISTANCE);
	}
	
	public void getBoxSix() {
		pilot.travel(THREE_BOX_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(TEN_CENTIMETRE_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(ONE_BOX_DISTANCE);
		pilot.travel(ONE_BOX_DISTANCE);
		pilot.travel(ONE_BOX_DISTANCE);
		
	}
	public void getBoxSeven() {
		pilot.travel(THREE_BOX_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(TEN_CENTIMETRE_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(THREE_BOX_DISTANCE);
		pilot.rotate(NINETY_DEGREE_TURN);
		pilot.travel(TWENTY_CENTIMETRE_DISTANCE);
		pilot.rotate(NINETY_DEGREE_TURN);
		pilot.travel(ONE_BOX_DISTANCE);
	}
	public void getBoxEight() {
		pilot.travel(THREE_BOX_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(TEN_CENTIMETRE_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(THREE_BOX_DISTANCE);
		pilot.rotate(NINETY_DEGREE_TURN);
		pilot.travel(TWENTY_CENTIMETRE_DISTANCE);
		pilot.rotate(NINETY_DEGREE_TURN);
		pilot.travel(ONE_BOX_DISTANCE);
		pilot.travel(ONE_BOX_DISTANCE);

	}
	public void getBoxNine() {
		pilot.travel(THREE_BOX_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(TEN_CENTIMETRE_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(THREE_BOX_DISTANCE);
		pilot.rotate(NINETY_DEGREE_TURN);
		pilot.travel(TWENTY_CENTIMETRE_DISTANCE);
		pilot.rotate(NINETY_DEGREE_TURN);
		pilot.travel(ONE_BOX_DISTANCE);
		pilot.travel(ONE_BOX_DISTANCE);
		pilot.travel(ONE_BOX_DISTANCE);
	}
	
}