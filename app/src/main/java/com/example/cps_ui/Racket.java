package com.example.cps_ui;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Racket {
    private float left;
    private float right;
    private float top;
    private float bottom;

    public Racket(float left, float top, float right , float bottom){
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    public void draw(Canvas canvas, Paint paint){
        canvas.drawRect(left, top, right, bottom, paint);
    }

}
