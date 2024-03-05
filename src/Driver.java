import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.hardware.port.MotorPort;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.utility.Delay;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;

public class Driver {
	public static void main(String[] args) {
		BaseRegulatedMotor mL = new EV3LargeRegulatedMotor(MotorPort.A);
		// Create a ”Wheel” with Diameter 51mm and offset 22mm left of centre.
		Wheel wLeft = WheeledChassis.modelWheel(mL, WHEEL_DIAMETER).offset(-AXLE_LENGTH / 2);
		BaseRegulatedMotor mR = new EV3LargeRegulatedMotor(MotorPort.B);
		// Create a ”Wheel” with Diameter 51mm and offset 22mm right of centre.
		Wheel wRight = WheeledChassis.modelWheel(mR, WHEEL_DIAMETER).offset(AXLE_LENGTH / 2);
		// Create a ”Chassis” with two wheels on it.
		Chassis chassis = new WheeledChassis((new Wheel[] {wRight, wLeft}),
		WheeledChassis.TYPE_DIFFERENTIAL);
		// Finally create a pilot which can drive using this chassis.
		MovePilot pilot = new MovePilot(chassis);
		PoseProvider poseProvider = new OdometryPoseProvider(pilot);
		
		Behavior lowBattery = new LowBattery();
		Behavior trundle = new Trundle(pilot);
		TicTacToe ticTacToe = new TicTacToe();
		// ticTacToe.start();
		Arbitrator ab = new Arbitrator(new Behavior[] {lowBattery, trundle});
		ab.go();
	}
}