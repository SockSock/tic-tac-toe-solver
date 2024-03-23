import lejos.hardware.Battery;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;

public class LowBattery implements Behavior {
	public LowBattery() {

	}
	
	@Override
	public boolean takeControl() {
		if (Battery.getVoltage() <= 6.5) {
			LCD.drawString("Battery is low :[", 0, 2);
			System.exit(-1); 
			return true;
		}
		
		return false;
	}

	@Override
	public void action() {

	}

	@Override
	public void suppress() {

	}
}
