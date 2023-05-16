package com.example.cps_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing our view.
        relativeLayout = findViewById(R.id.idRLView);

        // calling our  paint view class and adding
        // its view to our relative layout.
        PaintView paintView = new PaintView(this);
        relativeLayout.addView(paintView);
    }
}