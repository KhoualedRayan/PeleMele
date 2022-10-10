package com.example.pelemele;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.Date;

public class ChronometreActivity extends AppCompatActivity {
    private long temps ;
    private ImageButton start;
    private ImageButton stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometre);
        start =(ImageButton) findViewById(R.id.imageButton_Start);
        stop =(ImageButton) findViewById(R.id.imageButton_Stop);
        Toast.makeText(ChronometreActivity.this, "Chrono", Toast.LENGTH_SHORT).show() ;
        stop.setEnabled(false);
    }

    public void stopOnClick(View view) {
        long millis = System.currentTimeMillis() - temps;
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        seconds     = seconds % 60;
        String text = String.format("%d:%02d", minutes, seconds);
        Toast.makeText(ChronometreActivity.this, "temps écoulé : "+ text, Toast.LENGTH_SHORT).show() ;
        start.setEnabled(true);
        stop.setEnabled(false);
    }

    public void startOnClick(View view) {
        temps = System.currentTimeMillis();
        Toast.makeText(ChronometreActivity.this, "Chrono lancé", Toast.LENGTH_SHORT).show() ;
        start.setEnabled(false);
        stop.setEnabled(true);

    }
}