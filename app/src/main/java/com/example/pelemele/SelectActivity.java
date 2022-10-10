package com.example.pelemele;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class SelectActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        imageView = findViewById(R.id.imageView);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.i("SelectActivity","nombre de contacts enregistrés : "+motionEvent.getPointerCount());
                if(motionEvent.getPointerCount() == 2){
                    Log.i("SelectActivity","x : "+ motionEvent.getX() + ", y : "+motionEvent.getY());
                }
                return false;
            }
        });
    }
}