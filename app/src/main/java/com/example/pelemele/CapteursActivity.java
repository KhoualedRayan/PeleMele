package com.example.pelemele;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class CapteursActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor sensor;
    private Switch simpleSwitch;
    private boolean isActivate = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capteurs);
        simpleSwitch = (Switch) findViewById(R.id.button_switch);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        simpleSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(simpleSwitch.isChecked()) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    isActivate =!isActivate;

                }
                
            }
        });
    }
}