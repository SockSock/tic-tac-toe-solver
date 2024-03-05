import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Driver {
	public static void main(String[] args) {
		TicTacToe ticTacToe = new TicTacToe();
		Behavior lowBattery = new LowBattery();
		ticTacToe.start();
		Arbitrator ab = new Arbitrator(new Behavior[] {lowBattery});
		ab.go();
	}
}