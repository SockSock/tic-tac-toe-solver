import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.hardware.port.SensorPort;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import lejos.hardware.sensor.EV3ColorSensor;

public class ColorIdentifier implements Behavior {
	float[] level = new float[3];
	private SampleProvider color;
	private PoseProvider poseProvider;
	private TicTacToe ticTacToe;
	private Trundle trundle;
	private float red;
	private float green;
	private float blue;
	
	public ColorIdentifier(SampleProvider color, PoseProvider poseProvider, TicTacToe ticTacToe, Trundle trundle) {
		this.color = color;
		this.poseProvider = poseProvider;
		this.ticTacToe = ticTacToe;
		this.trundle = trundle;
	}
	
	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		long startTime = this.trundle.getStartTime();
		this.color.fetchSample(level, 0);

		red = level[0];
		green = level[1];
		blue = level[2];
		
		// System.out.println(red + " " + green + " " + blue);
		
		if (red > 0.18 && red < 0.32 && green > 0.16 && green < 0.30 && blue > 0.16 && blue < 0.30) {
			// System.out.println("White");
		} else if (Math.max(red, Math.max(green, blue)) == red && !this.trundle.getFlag() && (System.currentTimeMillis() - startTime) < 27000) {
			float[] test = calculatePosition(System.currentTimeMillis() - startTime);
			while (test == null) {
				test = calculatePosition(System.currentTimeMillis() - startTime);
			}
			this.ticTacToe.start(test);
			while (!this.trundle.getFlag()) {
				Delay.msDelay(1);
			}
			
			} else {
				float intensity = red + green + blue;
				if (intensity < 0.5 && (red < 0.15 || green < 0.15 || blue < 0.15)) {
					System.out.println("Black");
				} else {
					System.out.println("Unknown");
				}
			}
	}

	@Override
	public void suppress() {

	}
	
	private float[] calculatePosition(long time) {   
	    float[] coords = new float[2];
	    System.out.println(time);
	    
	    if (time > 10 && time < 2111) {
	        if (this.ticTacToe.isValidMove(0, 0)) {
	            coords[0] = 0;
	            coords[1] = 0;
	            return coords;
	        }
	    } else if (time > 2111 && time < 2900) {
	        if (this.ticTacToe.isValidMove(0, 1)) {
	            coords[0] = 0;
	            coords[1] = 1;
	            return coords;
	        }
	    } else if (time > 2900 && time < 4122) {
	        if (this.ticTacToe.isValidMove(0, 2)) {
	            coords[0] = 0;
	            coords[1] = 2;
	            return coords;
	        }
	    } else if (time > 11828 && time < 12828) {
	        if (this.ticTacToe.isValidMove(1, 2)) {
	            coords[0] = 1;
	            coords[1] = 2;
	            return coords;
	        }
	    } else if (time > 12828 && time < 14000) {
	        if (this.ticTacToe.isValidMove(1, 1)) {
	            coords[0] = 1;
	            coords[1] = 1;
	            return coords;
	        }
	    } else if (time > 14000 && time < 15000) {
	        if (this.ticTacToe.isValidMove(1, 0)) {
	            coords[0] = 1;
	            coords[1] = 0;
	            return coords;
	        }
	    } else if (time > 23000 && time < 24000) {
	        if (this.ticTacToe.isValidMove(2, 0)) {
	            coords[0] = 2;
	            coords[1] = 0;
	            return coords;
	        }
	    } else if (time > 24000 && time < 25000) {
	        if (this.ticTacToe.isValidMove(2, 1)) {
	            coords[0] = 2;
	            coords[1] = 1;
	            return coords;
	        }
	    } else if (time > 25000 && time < 26000) {
	        if (this.ticTacToe.isValidMove(2, 2)) {
	            coords[0] = 2;
	            coords[1] = 2;
	            return coords;
	        }
	    }
	    
	    
	    return null;
	}
}
