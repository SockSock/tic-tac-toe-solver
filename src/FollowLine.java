import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;


public class FollowLine {
	
	public static final float LIGHT_AVERAGE = 0.657f;

	public static void main(String[] args) {
		float[] level = new float[1];
		float maxColorLevel = Float.MIN_VALUE;
		float minColorLevel = Float.MAX_VALUE;
		
		BaseRegulatedMotor mLeft = new EV3LargeRegulatedMotor(MotorPort.A);
		BaseRegulatedMotor mRight = new EV3LargeRegulatedMotor(MotorPort.B);
		
		mLeft.setSpeed(70);
		mRight.setSpeed(70);
		
		EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S1);
		SampleProvider color = sensor.getRedMode();
		
		while (!Button.ENTER.isDown()) {
			color.fetchSample(level, 0);
			LCD.drawString(Float.toString(level[0]), 0, 0);
			Delay.msDelay(100);
		}
	}
}