<<<<<<< HEAD
import lejos.hardware.Button;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Trundle implements Behavior {
	private MovePilot pilot;
	
	public Trundle(MovePilot pilot) {
		this.pilot = pilot;
	}

	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		this.pilot.forward();
	}

	@Override
	public void suppress() {
		
	}
}
=======
import lejos.hardware.Button;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Trundle implements Behavior {
	private MovePilot pilot;
	
	public Trundle(MovePilot pilot) {
		this.pilot = pilot;
	}

	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		this.pilot.forward();
	}

	@Override
	public void suppress() {
		
	}
}
>>>>>>> branch 'master' of https://github.com/SockSock/tic-tac-toe-solver.git
