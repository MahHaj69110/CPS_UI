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

    public GameLoop(PongView view, int dt, Pair<Integer,Integer> screen){
        this.view = view;
        this.running = true;
        this.deltaT = dt;
        Sensor sensor;
        racket = new Racket(
                screen.first / 3,
                10 + 3* screen.second / 4,
                2* screen.first / 3,
                3* screen.second / 4);
        ball = new Ball(screen.first / 2,0, view.getArcLeft());
        view.setBall(ball);
        view.setRacket(racket);

    }

    @Override
    public void run() {
        super.run();
        while (running) {
            try {
                view.updateScreen();
                ball.update();
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
