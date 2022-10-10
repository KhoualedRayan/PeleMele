package com.example.pelemele;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VoirPhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_photo);
        FileInputStream fis = null;
        try {
            fis = openFileInput("image.data");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView = findViewById(R.id.lastPic);
        Bitmap bm = BitmapFactory.decodeStream(fis);
        // Reste à mettre bm à mettre sur l’ImageView
        imageView.setImageBitmap(bm);
    }
}