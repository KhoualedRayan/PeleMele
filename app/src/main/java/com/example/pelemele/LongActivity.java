package com.example.pelemele;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LongActivity extends AppCompatActivity {
    private ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long);
        pb = findViewById(R.id.progressBar) ;
    }


    public void activityOnClick(View view) {
         Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Timer time =new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.i("LongActivity","Fin de tache");
                                pb.setVisibility(View.INVISIBLE);
                            }
                        });
                    }
                };
                time.schedule(task,5000);
            }
        };
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(runnable);
        pb.setVisibility(View.VISIBLE);


    }
}