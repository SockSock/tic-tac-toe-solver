import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;

public class EmergencyStop implements Behavior {
    private NXTSoundSensor soundSensor;
    private SampleProvider soundProvider;
    private float[] sample;
    private boolean suppressed = false;

    private static final float SOUND_THRESHOLD = 0.8f; // 0.8f

    public EmergencyStop() {
        this.soundSensor = new NXTSoundSensor(SensorPort.S4);
        this.soundProvider = soundSensor.getDBAMode();
        this.sample = new float[soundProvider.sampleSize()];
    }

    @Override
    public boolean takeControl() {
        soundProvider.fetchSample(sample, 0);
        float soundLevel = sample[0];

        return soundLevel > SOUND_THRESHOLD;
    }

    @Override
    public void action() {
        suppressed = false;
        System.exit(-1); 
    }

    @Override
    public void suppress() {
        suppressed = true;
        
    }
}

