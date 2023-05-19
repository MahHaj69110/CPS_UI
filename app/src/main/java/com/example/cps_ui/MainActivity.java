package com.example.cps_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.cps_ui.loop.GameLoop;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private GameLoop gameLoop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton restartGameBtn = (FloatingActionButton) findViewById(R.id.restart_game_btn);
        Intent intent = getIntent();
        String sensorType = intent.getStringExtra("sensor_type");
        restartGameBtn.setOnClickListener(v -> {
            gameLoop.pushBall();
        });

        gameView = (GameView) findViewById(R.id.game_view);
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Pair<Integer,Integer> screen = new Pair<>(displayMetrics.widthPixels, displayMetrics.heightPixels);
        gameLoop = new GameLoop(gameView,sensorManager,sensorType, 16,screen);
        gameLoop.start();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // initializing our view.
        relativeLayout = findViewById(R.id.idConstView);

        // calling our  paint view class and adding
        // its view to our relative layout.
        PongView paintView = new PongView(this);
        relativeLayout.addView(paintView);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gameLoop.endLoop();
        while (gameLoop.isAlive());
    }
}