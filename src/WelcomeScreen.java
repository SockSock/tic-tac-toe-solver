import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class WelcomeScreen {
	public WelcomeScreen() {
		
	}

    public void displayWelcomeScreen(String author1, String author2, String author3, String version) {
        LCD.drawString("Welcome!", 2, 0);
        LCD.drawString("Authors:", 1, 2);
        LCD.drawString(author1, 1, 3);
        LCD.drawString(author2, 1, 4);
        LCD.drawString(author3, 1, 5);
        LCD.drawString(version, 1, 7);
    }

    public void waitForButtonPress() {
        Button.waitForAnyPress();
        Delay.msDelay(100);
        LCD.clear();
    }
}

