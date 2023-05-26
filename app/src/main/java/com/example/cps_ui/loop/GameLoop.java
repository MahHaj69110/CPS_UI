package com.example.cps_ui.loop;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import androidx.core.util.Pair;
import com.example.cps_ui.Ball;
import com.example.cps_ui.PongView;
import com.example.cps_ui.Racket;
import com.example.cps_ui.locationCalculator.AccelerometerLocationCalculator;
import com.example.cps_ui.locationCalculator.GyroscopeLocationCalculator;

public class GameLoop extends Thread{

    private boolean running;
    private PongView view;
    private Ball ball;
    private Racket racket;
    private AccelerometerLocationCalculator accelerometerLocationCalculator;
    private GyroscopeLocationCalculator gyroscopeLocationCalculator;
    private int deltaT;

    public GameLoop(PongView view, SensorManager sensorManager, String sensorType, int dt, Pair<Integer,Integer> screen){
        this.view = view;
        this.running = true;
        this.deltaT = dt;
        Sensor sensor;
        Racket racket = new Racket(
                view.getLeft() + view.getWidth() / 3,
                view.getTop() + 3* view.getHeight() / 4,
                view.getRight() - view.getWidth() / 3,
                view.getBottom() - view.getHeight() / 4);
        Ball ball = new Ball(view.getWidth() / 2,view.getHeight() / 2, view.getArcLeft());

        MovementRecognizer movementRecognizer = new MovementRecognizer(screen.getFirst(),screen.getSecond(),ball.getRadius());
        if (sensorType.equals("gravity")){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
            accelerometerLocationCalculator = new AccelerometerLocationCalculator(sensorManager,sensor,state,movementRecognizer);
        }
        else {
            state.setAngles(new Pair<>(0.0, 0.0));
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            gyroscopeLocationCalculator = new GyroscopeLocationCalculator(sensorManager,sensor,state,movementRecognizer);
        }
    }

    @Override
    public void run() {
        super.run();
        while (running) {
            try {
                Pair<Float,Float> ballPos = ball.getPosition();
                view.updateScreen();
                Pair<Float, Float> pos = accelerometerLocationCalculator.nextCoordinate(deltaT);
                ball.updateLocation(pos);
                Thread.sleep(deltaT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void endLoop(){
        running = false;
    }
}
