package com.example.cps_ui;
// CustomView.java
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CustomView extends View {
    private Paint paint;

    public CustomView(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Get the dimensions of the view
        int viewWidth = getWidth();
        int viewHeight = getHeight();

        // Calculate the center coordinates of the view
        int centerX = viewWidth / 2;
        int centerY = viewHeight / 2;

        // Calculate the radius of the ball
        int radius = Math.min(centerX, centerY) - 50;

        // Draw the ball on the canvas
        canvas.drawCircle(centerX, centerY, radius, paint);
    }
}
