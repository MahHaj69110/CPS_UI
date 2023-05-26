package com.example.cps_ui;

import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.util.Pair;

public class Ball {
    private float cx;
    private float cy;
    private float radius;

    public Ball(float centerX, float centerY, float radius) {
        this.cx = centerX;
        this.cy = centerY;
        this.radius = radius;
    }

    public void draw(Canvas canvas, Paint paint){
        canvas.drawCircle(cx, cy, radius, paint);
    }

    public void updateLocation(Pair<Float, Float> pos) {
        this.cx = pos.first;
        this.cy = pos.second;
    }

    public Pair<Float, Float> getPosition() {
        return Pair.create(cx,cy);
    }
}
