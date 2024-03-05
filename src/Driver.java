<<<<<<< HEAD
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Driver {
	public static void main(String[] args) {
		Behavior lowBattery = new LowBattery();
		Arbitrator ab = new Arbitrator(new Behavior[] {lowBattery});
		ab.go();
	}
=======
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Driver {
	public static void main(String[] args) {
		Behavior lowBattery = new LowBattery();
		Arbitrator ab = new Arbitrator(new Behavior[] {lowBattery});
		ab.go();
	}
>>>>>>> branch 'master' of https://github.com/SockSock/tic-tac-toe-solver.git
}