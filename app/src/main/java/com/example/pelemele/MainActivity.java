package com.example.pelemele;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int PHOTO = 1;
    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button chrono = findViewById(R.id.button_chrono);
        Button longActivity = (Button) findViewById(R.id.button_long_activity);
        Button meteo = findViewById(R.id.button_meteo);
        Button lastPhoto = findViewById(R.id.button_getPhoto);
        Button capteurs = findViewById(R.id.button_sensor);
        Button contact = findViewById(R.id.button_contact);
        Button select_activity = (Button) findViewById(R.id.button_selectActivity);
        Button photo = findViewById(R.id.button_photo);
        sendIntent(lastPhoto,VoirPhotoActivity.class);
        sendIntent(chrono,ChronometreActivity.class);
        sendIntent(longActivity, LongActivity.class);
        sendIntent(contact, ContactAcitivity.class);
        sendIntent(meteo, MeteoActivity.class);
        sendIntent(capteurs, CapteursActivity.class);
        sendIntent(select_activity, SelectActivity.class);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this,PhotoActivity.class),PHOTO);
            }
        });
    }


    public void sendIntent(Button button, Class c){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, c));
            }
        });
    }


    public void bonjourOnClick(View view) {
        Toast.makeText(MainActivity.this, "Bonjour", Toast.LENGTH_SHORT).show() ;
        Log.i("MainActivity","une info");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Quit_Item:
                System.exit(0);
                return true;
            case R.id.Suspendre_Item:
                finish();
                return true;
            case R.id.Chrono_Item:
                startActivity(new Intent(MainActivity.this, ChronometreActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}