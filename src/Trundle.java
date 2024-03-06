import lejos.hardware.Button;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Trundle implements Behavior {
	private MovePilot pilot;
	private Map map;
	private final float HALF_METRE_DISTANCE = 500;
	private final float TEN_CENTIMETRE_DISTANCE = 100;
	private final float NINETY_DEGREE_TURN = 200;
	
	public Trundle(MovePilot pilot, Map map) {
		this.pilot = pilot;
		this.map = map;
	}

	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
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

	@Override
	public void suppress() {
		
	}
}