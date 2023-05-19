package com.example.cps_ui.loop;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;

import com.example.cps_ui.Ball;
import com.example.cps_ui.PongView;

import java.util.Random;

import Coordinates.Ball;
import LocationCalculator.LocationCalculator;
import LocationCalculator.GyroscopeLocationCalculator;
import LocationCalculator.GravityLocationCalculator;
import LocationCalculator.MovementRecognizer;

import Model.Pair;
import Model.State;

public class GameLoop extends Thread{

    private boolean running;
    private PongView view;
    private Ball ball;
    private LocationCalculator calculator;
    private int deltaT;

    public GameLoop(PongView view, SensorManager sensorManager, String sensorType, int dt, Pair<Integer,Integer> screen){
        this.view = view;
        this.running = true;
        this.deltaT = dt;
        Sensor sensor;
        ball = new Ball();
        MovementRecognizer movementRecognizer = new MovementRecognizer(screen.getFirst(),screen.getSecond(),ball.getRadius());
        if (sensorType.equals("gravity")){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
            calculator = new GravityLocationCalculator(sensorManager,sensor,state,movementRecognizer);
        }
        else {
            state.setAngles(new Pair<>(0.0, 0.0));
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            calculator = new GyroscopeLocationCalculator(sensorManager,sensor,state,movementRecognizer);
        }
    }
    @Override
    public void run() {
        super.run();
        while (running) {
            try {
                Pair<Integer,Integer> ballPos = ball.getPosition();
                view.updateBallPosition(ballPos.getFirst(),ballPos.getSecond());
                Pair<Double, Double> pos = calculator.nextCoordinate(deltaT);
                ball.updateLocation(pos);
                Thread.sleep(deltaT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void pushBall(){
        calculator.setVelocity();
    }

    public void endLoop(){
        running = false;
    }
}
