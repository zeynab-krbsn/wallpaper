package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageModel> modelClassList;
    private RecyclerView recyclerView;
    Adapter adapter;
    LinearLayout LYNature, LYPets, LYBuilding, LYFantasy, LYCar, LYClassic, LYArt, LYMusic;
    EditText editText;
    ImageButton search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        LYNature=findViewById(R.id.LYNature);
        LYPets=findViewById(R.id.LYPets);
        LYBuilding=findViewById(R.id.LYBuilding);
        LYFantasy=findViewById(R.id.LYFantasy);
        LYCar=findViewById(R.id.LYCar);
        LYClassic=findViewById(R.id.LYClassic);
        LYArt=findViewById(R.id.LYArt);
        LYMusic=findViewById(R.id.LYMusic);

        editText=findViewById(R.id.editText);
        search=findViewById(R.id.search);

        LYNature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageByCategory("nature");
            }
        });
        LYPets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageByCategory("pets");
            }
        });
        LYBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageByCategory("building");
            }
        });
        LYFantasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageByCategory("fantasy");
            }
        });
        LYClassic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageByCategory("classic");
            }
        });
        LYCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageByCategory("car");
            }
        });
        LYArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageByCategory("art");
            }
        });
        LYMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageByCategory("music");
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query =editText.getText().toString().trim().toLowerCase();
                if (query.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Enter Something",Toast.LENGTH_SHORT).show();
                }
                else {
                    getImageByCategory(query);
                }
            }
        });

    }

    private void getImageByCategory(String query) {

        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
        intent.putExtra("query",query);
        startActivity(intent);
    }

//    private void trending() {
//
//        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
//        startActivity(intent);
//    }
}