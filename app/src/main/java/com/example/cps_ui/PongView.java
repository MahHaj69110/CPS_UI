package com.example.cps_ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;

public class PongView extends View {

    // below we are creating variables for our paint
    Paint otherPaint, outerPaint, textPaint;

    // and a floating variable for our left arc.
    float arcLeft;

    private Ball ball;
    private Racket racket;

    @SuppressLint("ResourceAsColor")
    public PongView(Context context) {
        super(context);

        // on below line we are initializing our paint variable for our text
        textPaint = new Paint(Paint.LINEAR_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

        // on below line we are setting color to it.
        textPaint.setColor(Color.WHITE);

        // on below line we are setting text size to it.
        // In Paint we have to add text size using px so
        // we have created a method where we are converting dp to pixels.
        textPaint.setTextSize(pxFromDp(context, 24));

        // on below line we are initializing our outer paint
        outerPaint = new Paint();

        // on below line we are setting style to our paint.
        outerPaint.setStyle(Paint.Style.FILL);

        // on below line we are creating a display metrics
        DisplayMetrics displayMetrics = new DisplayMetrics();

        // on below line we are getting display metrics.
        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);

        // on below line we are assigning
        // the value to the arc left.
        arcLeft = pxFromDp(context, 15);

        // on below line we are creating
        // a new variable for our paint
        otherPaint = new Paint();
        // on below line we are setting color to our paint.
        otherPaint.setColor(Color.WHITE);

        // on below line we are setting style to out paint.
        otherPaint.setStyle(Paint.Style.FILL);
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public void setRacket(Racket racket) {
        this.racket = racket;
    }

    // below method is use to generate px from DP.
    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // below four lines of code is use to add
        // back color to our screen which is green
        canvas.drawPaint(outerPaint);

        canvas.save();
//        canvas.rotate(10);
        racket.draw(canvas,otherPaint);
        canvas.restore();

        // on below line we are drawing a circle and passing
        // width, height, left arc and paint to add color.
        ball.draw(canvas,otherPaint);

        // on below line we are adding text using paint in our canvas.
        canvas.drawText("Pong", (float) (getWidth() * 0.43), (float) (getHeight() * 0.97), textPaint);
    }
    public void updateScreen(){
        invalidate();
    }

    public float getArcLeft() {
        return arcLeft;
    }
}

