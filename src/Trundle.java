import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;

public class Trundle implements Behavior {
	private EV3TouchSensor touchSensor = new EV3TouchSensor(SensorPort.S3);
	private SampleProvider touch = touchSensor.getTouchMode();
	private BaseRegulatedMotor arm;
	private MovePilot pilot;
	private TicTacToe ticTacToe;
	private boolean flag = false;
	private boolean goBack;
	private long startTime = 0;
	private String retreat;
	private float[] samples = new float[1];
	private final float THREE_BOX_DISTANCE = 380; // 380
	private final float ONE_BOX_DISTANCE = 65;// 380
	private final float TEN_CENTIMETRE_DISTANCE = 40; // 40
	private final float HALF_BOX_DISTANCE = 40; // 50
	private final float NINETY_DEGREE_TURN = 200; // 200
	private final int THE_FLICK = 90;
	
	public Trundle(MovePilot pilot, TicTacToe ticTacToe, BaseRegulatedMotor arm) {
		this.pilot = pilot;
		this.ticTacToe = ticTacToe;
		this.arm = arm;
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
		if (!flag) {
			startTime = System.currentTimeMillis();
			
			pilot.travel(THREE_BOX_DISTANCE);
			pilot.rotate(-NINETY_DEGREE_TURN);
			pilot.travel(TEN_CENTIMETRE_DISTANCE);
			pilot.travel(TEN_CENTIMETRE_DISTANCE);
			pilot.rotate(-NINETY_DEGREE_TURN);
			pilot.travel(THREE_BOX_DISTANCE);
			pilot.rotate(NINETY_DEGREE_TURN);
			pilot.travel(TEN_CENTIMETRE_DISTANCE);
			pilot.travel(TEN_CENTIMETRE_DISTANCE);
			pilot.travel(HALF_BOX_DISTANCE);
			pilot.rotate(NINETY_DEGREE_TURN);
			pilot.travel(THREE_BOX_DISTANCE);
			pilot.rotate(NINETY_DEGREE_TURN);
			pilot.travel(TEN_CENTIMETRE_DISTANCE);
			pilot.travel(TEN_CENTIMETRE_DISTANCE);
			pilot.travel(TEN_CENTIMETRE_DISTANCE);
			pilot.travel(TEN_CENTIMETRE_DISTANCE);
			pilot.travel(HALF_BOX_DISTANCE);
			pilot.rotate(NINETY_DEGREE_TURN);
			pilot.travel(THREE_BOX_DISTANCE);
			pilot.rotate(-NINETY_DEGREE_TURN);
			pilot.rotate(-NINETY_DEGREE_TURN);
			
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
		arm.rotate(THE_FLICK);
		pilot.travel(HALF_BOX_DISTANCE);
		arm.rotate(-THE_FLICK);
		pilot.travel(-HALF_BOX_DISTANCE);
		
	}
	
	public void getBoxTwo() {
		pilot.travel(ONE_BOX_DISTANCE);
		arm.rotate(THE_FLICK);
		pilot.travel(HALF_BOX_DISTANCE);
		arm.rotate(-THE_FLICK);
		pilot.travel(-HALF_BOX_DISTANCE);
		pilot.travel(-ONE_BOX_DISTANCE);
	}
	
	public void getBoxThree() {
		pilot.travel(ONE_BOX_DISTANCE);
		pilot.travel(ONE_BOX_DISTANCE);
		arm.rotate(THE_FLICK);
		pilot.travel(HALF_BOX_DISTANCE);
		arm.rotate(-THE_FLICK);
		pilot.travel(-HALF_BOX_DISTANCE);
		pilot.travel(-ONE_BOX_DISTANCE);
		pilot.travel(-ONE_BOX_DISTANCE);
	}
	
	public void getBoxFour() {
		pilot.travel(ONE_BOX_DISTANCE);
		pilot.travel(ONE_BOX_DISTANCE);
		pilot.travel(ONE_BOX_DISTANCE);
		arm.rotate(THE_FLICK);
		pilot.travel(HALF_BOX_DISTANCE);
		arm.rotate(-THE_FLICK);
		pilot.travel(-HALF_BOX_DISTANCE);
		pilot.travel(-ONE_BOX_DISTANCE);
		pilot.travel(-ONE_BOX_DISTANCE);
		pilot.travel(-ONE_BOX_DISTANCE);
	}
	public void getBoxFive() {
		pilot.travel(THREE_BOX_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(TEN_CENTIMETRE_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(TEN_CENTIMETRE_DISTANCE);
		arm.rotate(THE_FLICK);
		pilot.travel(HALF_BOX_DISTANCE);
		arm.rotate(-THE_FLICK);
		pilot.travel(-HALF_BOX_DISTANCE);
		pilot.travel(-TEN_CENTIMETRE_DISTANCE);
		pilot.rotate(NINETY_DEGREE_TURN);
		pilot.travel(-TEN_CENTIMETRE_DISTANCE);
		pilot.rotate(NINETY_DEGREE_TURN);
		pilot.travel(-THREE_BOX_DISTANCE);
	}
	
	public void getBoxSix() {
		pilot.travel(THREE_BOX_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(TEN_CENTIMETRE_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(ONE_BOX_DISTANCE);
		pilot.travel(ONE_BOX_DISTANCE);
		arm.rotate(THE_FLICK);
		pilot.travel(HALF_BOX_DISTANCE);
		arm.rotate(-THE_FLICK);
		pilot.travel(-HALF_BOX_DISTANCE);
		pilot.travel(-ONE_BOX_DISTANCE);
		pilot.travel(-ONE_BOX_DISTANCE);
		pilot.rotate(NINETY_DEGREE_TURN);
		pilot.travel(-TEN_CENTIMETRE_DISTANCE);
		pilot.rotate(NINETY_DEGREE_TURN);
		pilot.travel(-THREE_BOX_DISTANCE);
	}
	
	public void getBoxSeven() {
		pilot.travel(THREE_BOX_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(TEN_CENTIMETRE_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(THREE_BOX_DISTANCE);
		pilot.travel(TEN_CENTIMETRE_DISTANCE);
		pilot.rotate(NINETY_DEGREE_TURN);
		pilot.travel(TEN_CENTIMETRE_DISTANCE);
		pilot.rotate(NINETY_DEGREE_TURN);
		arm.rotate(THE_FLICK);
		pilot.travel(HALF_BOX_DISTANCE);
		arm.rotate(-THE_FLICK);
		pilot.travel(-HALF_BOX_DISTANCE);
		pilot.travel(-ONE_BOX_DISTANCE);
		pilot.travel(-ONE_BOX_DISTANCE);
		pilot.travel(-ONE_BOX_DISTANCE);
		pilot.rotate(NINETY_DEGREE_TURN);
		pilot.travel(-TEN_CENTIMETRE_DISTANCE);
		pilot.rotate(NINETY_DEGREE_TURN);
		pilot.travel(-THREE_BOX_DISTANCE);
		
	}
	public void getBoxEight() {
		pilot.travel(THREE_BOX_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(TEN_CENTIMETRE_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(THREE_BOX_DISTANCE);
		pilot.rotate(NINETY_DEGREE_TURN);
		pilot.travel(TEN_CENTIMETRE_DISTANCE);
		pilot.rotate(NINETY_DEGREE_TURN);
		pilot.travel(ONE_BOX_DISTANCE);
		arm.rotate(THE_FLICK);
		pilot.travel(HALF_BOX_DISTANCE);
		arm.rotate(-THE_FLICK);
		pilot.travel(-HALF_BOX_DISTANCE);
		pilot.travel(-ONE_BOX_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(-TEN_CENTIMETRE_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(-THREE_BOX_DISTANCE);
		pilot.rotate(NINETY_DEGREE_TURN);
		pilot.travel(-TEN_CENTIMETRE_DISTANCE);
		pilot.rotate(NINETY_DEGREE_TURN);
		pilot.travel(-THREE_BOX_DISTANCE);
	}
	public void getBoxNine() {
		pilot.travel(THREE_BOX_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(TEN_CENTIMETRE_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(THREE_BOX_DISTANCE);
		pilot.rotate(NINETY_DEGREE_TURN);
		pilot.travel(TEN_CENTIMETRE_DISTANCE);
		pilot.rotate(NINETY_DEGREE_TURN);
		pilot.travel(ONE_BOX_DISTANCE);
		pilot.travel(ONE_BOX_DISTANCE);
		arm.rotate(THE_FLICK);
		pilot.travel(HALF_BOX_DISTANCE);
		arm.rotate(-THE_FLICK);
		pilot.travel(-HALF_BOX_DISTANCE);
		pilot.travel(-ONE_BOX_DISTANCE);
		pilot.travel(-ONE_BOX_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(-TEN_CENTIMETRE_DISTANCE);
		pilot.rotate(-NINETY_DEGREE_TURN);
		pilot.travel(-THREE_BOX_DISTANCE);
		pilot.rotate(NINETY_DEGREE_TURN);
		pilot.travel(-TEN_CENTIMETRE_DISTANCE);
		pilot.rotate(NINETY_DEGREE_TURN);
		pilot.travel(-THREE_BOX_DISTANCE);
	}

	
}