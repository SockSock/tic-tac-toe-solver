<<<<<<< HEAD
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
			LCD.drawString("Battery is low bro, you gotta recharge me fr fr", 0, 2);
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
=======
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
			LCD.drawString("Battery is low bro, you gotta recharge me fr fr", 0, 2);
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
>>>>>>> branch 'master' of https://github.com/SockSock/tic-tac-toe-solver.git
