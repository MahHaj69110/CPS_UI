package com.example.cps_ui;

import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.util.Pair;

public class Ball {
    private Pair<Float, Float> position;
    private Pair<Float, Float> velocity;
    private float radius;
    private final float GRAVITY_AC = 100;
    private final float DELTA_T = 0.03f;

    public Ball(float centerX, float centerY, float radius) {
        this.position = Pair.create(centerX,centerY);
        this.velocity = Pair.create(0f,0f);
        this.radius = radius;
    }

    public void draw(Canvas canvas, Paint paint){
        canvas.drawCircle(position.first, position.second, radius, paint);
    }

    public void setPosition(Pair<Float, Float> position) {
        this.position = position;
    }

    public Pair<Float, Float> getPosition() {
        return position;
    }

    public void update(){
        Pair<Float, Float> newVelocity = Pair.create(velocity.first, velocity.second + GRAVITY_AC * DELTA_T);
        Pair<Float, Float> deltaPosiotion = Pair.create(newVelocity.first * DELTA_T, newVelocity.second * DELTA_T);
        Pair<Float, Float> newPosiotion = Pair.create(position.first + deltaPosiotion.first, position.second+deltaPosiotion.second);
        // check Wall
        // check Racket
        velocity = newVelocity;
        position = newPosiotion;
    }
}
