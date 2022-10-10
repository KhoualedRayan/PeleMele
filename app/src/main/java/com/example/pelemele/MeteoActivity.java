package com.example.pelemele;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MeteoActivity extends AppCompatActivity {
    private FusedLocationProviderClient fusedLocationClient;
    private int locationRequestCode = 1000;
    private double Latitude = 0.0, Longitude = 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meteo);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    public void getPositionOnClick(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, locationRequestCode);
        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, locationRequestCode);
    }
    @SuppressLint("MissingPermission")
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
                        if (location != null) {
                            Latitude = location.getLatitude();
                            Longitude = location.getLongitude();

                            Runnable runnable = new Runnable() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            String url = "https://api.openweathermap.org/data/2.5/weather?lat="+Latitude+"&lon="+Longitude+".99&appid=517322367ca6e3312c2fdb7abbac0947";
                                            InputStream in;
                                            try {
                                                in = new java.net.URL(url).openStream();
                                                JSONObject res = readStream(in) ;
                                                //BUG SUR UN TOAST
                                            } catch (IOException | JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            };
                            ExecutorService service = Executors.newSingleThreadExecutor();
                            service.execute(runnable);


                            Toast.makeText(MeteoActivity.this, "Longitude : " + Longitude + " Latitude : " + Latitude, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
            }
        }
    }

    private JSONObject readStream(InputStream is) throws IOException, JSONException{
        StringBuilder sb = new StringBuilder();
        BufferedReader r =  new BufferedReader(new InputStreamReader(is),1000);
        for(String line = r.readLine(); line!=null;line = r.readLine()){
            sb.append(line);
        }
        is.close();
        return new JSONObject(sb.toString());
    }
}