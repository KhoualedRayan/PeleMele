package com.example.pelemele;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PhotoActivity extends AppCompatActivity {

    private static final int PHOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE) ;
        if (intent.resolveActivity(getPackageManager())!=null) {
            Toast.makeText(PhotoActivity.this, "demande", Toast.LENGTH_SHORT).show() ;
            startActivityForResult(intent,PHOTO);
            setResult(RESULT_OK,intent);
        }
    }
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitMap = (Bitmap) extras.get("data");
            Toast.makeText(PhotoActivity.this, "Hauteur de la photo : " + imageBitMap.getHeight(), Toast.LENGTH_SHORT).show();
            FileOutputStream fos;
            try {
                fos = openFileOutput("image.data", MODE_PRIVATE);
                imageBitMap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}