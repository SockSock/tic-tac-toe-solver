import java.util.Random;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.NXTColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class ColorIdentifier implements Behavior{
	float[] level = new float[1];
	float max = Float.MIN_VALUE;
	float min = Float.MAX_VALUE;
	float average = Float.MAX_VALUE;
	private final float[] BLUE = {0.12f, 0.2f};
	private final float[] BLACK = {0f, 0.1f};
	private final float[] RED = {0.3f, 0.5f};
	NXTColorSensor sensor = new NXTColorSensor(SensorPort.S1);
	SampleProvider color = sensor.getRedMode();
	
	@Override
	public boolean takeControl() {
		color.fetchSample(level,0);
		if (level[0]> max) {
			max = level[0];
		}
		if (level[0] < min) {
			min = level[0];
		}
		
		average = (max + min) / 2;
		
		return (level[0] < 0.20f);
	}

	@Override
	public void action() {
		Delay.msDelay(250);
		LCD.drawString(Float.toString(min), 0, 0);
		LCD.drawString(Float.toString(max), 0, 2);
		LCD.drawString("Average: " + Float.toString(average), 0, 4);
	}

	@Override
	public void suppress() {

	}
}