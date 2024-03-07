import lejos.hardware.Button;
import lejos.hardware.Sounds;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Trundle implements Behavior {
	private EV3TouchSensor touchSensor = new EV3TouchSensor(SensorPort.S2);
	private SampleProvider touch = touchSensor.getTouchMode();
	private MovePilot pilot;
	private Map map;
	private boolean flag = false;
	private float[] samples = new float[1];
	private final float HALF_METRE_DISTANCE = 500;
	private final float TEN_CENTIMETRE_DISTANCE = 100;
	private final float NINETY_DEGREE_TURN = 200;
	
	public Trundle(MovePilot pilot, Map map) {
		this.pilot = pilot;
		this.map = map;
	}

	@Override
	public boolean takeControl() {
		touch.fetchSample(samples, 0);
		if (samples[0] > 0) {
			flag = false;
			return true;
		}
		Delay.msDelay(50);
		
		return false;
	}

	@Override
	public void action() {
		if (flag) {
			
		} else {
			for (int i = 0; i < 2; i++) {
				pilot.travel(HALF_METRE_DISTANCE);
				pilot.rotate(NINETY_DEGREE_TURN);
				pilot.travel(TEN_CENTIMETRE_DISTANCE);
				pilot.rotate(NINETY_DEGREE_TURN);
				pilot.travel(HALF_METRE_DISTANCE);
				pilot.rotate(-NINETY_DEGREE_TURN);
				pilot.travel(TEN_CENTIMETRE_DISTANCE);
				pilot.rotate(-NINETY_DEGREE_TURN);
				this.map.move();
			}
		}
		
		flag = true;
	}

	@Override
	public void suppress() {
		
	}
}