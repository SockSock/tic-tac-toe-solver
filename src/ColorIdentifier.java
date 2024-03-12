import java.util.Random;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.NXTColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class ColorIdentifier implements Behavior {
	float[] level = new float[3];
	private SampleProvider color;
	private float max = Float.MIN_VALUE;
	
	public ColorIdentifier(SampleProvider color) {
		this.color = color;
	}
	
	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		this.color.fetchSample(level, 0);
		max = Math.max(level[0], Math.max(level[1], level[2]));
		
		if (level[0] < 0.1f && level[1] < 0.1f && level[2] < 0.1f) {
			System.out.println("BLACK");
		} else if (max == level[0]) {
			System.out.println("RED");
		} else if (max == level[1]) {
			System.out.println("GREEN");
		} else if (max == level[2]) {
			System.out.println("BLUE");
		} // put else then if statement here, move the above here
	}

	@Override
	public void suppress() {

	}
}