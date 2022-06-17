package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SetWallpaper extends AppCompatActivity {

    Intent intent;
    ImageView imageView;
    ImageButton set , download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_wallpaper);

        ActivityCompat.requestPermissions(SetWallpaper.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        ActivityCompat.requestPermissions(SetWallpaper.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final WallpaperManager wallpaperManager=WallpaperManager.getInstance(getApplicationContext());
        set=findViewById(R.id.set);
        download=findViewById(R.id.download);
        imageView=findViewById(R.id.finalImage);
        intent=getIntent();

        String url=intent.getStringExtra("image");
        Glide.with(getApplicationContext()).load(url).into(imageView);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
                    wallpaperManager.setBitmap(bitmap);
                    Toast.makeText(getApplicationContext(), "DONE", Toast.LENGTH_SHORT).show();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveInGallery();
                Toast.makeText(getApplicationContext(), "downloaded", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveInGallery() {
        BitmapDrawable bitmapDrawable = (BitmapDrawable)imageView.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();

        FileOutputStream outputStream=null;
        File dir = new File(Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES).getAbsolutePath()+"/Pexels_Wallpaper/");
        dir.mkdirs();

        String fileName=String.format("%d.jpg",System.currentTimeMillis());
        File outFile=new File(dir,fileName);
        try {
            outputStream =new FileOutputStream(outFile);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        try {
            outputStream.flush();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try {
            outputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}