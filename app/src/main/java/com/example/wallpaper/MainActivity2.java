package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {

    private ArrayList<ImageModel> modelClassList;
    private RecyclerView recyclerView;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView=findViewById(R.id.recyclerView);

        modelClassList=new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        adapter=new Adapter(getApplicationContext(),modelClassList);
        recyclerView.setAdapter(adapter);


            String query;
            Bundle extra = getIntent().getExtras();
            query = extra.getString("query");
            getImageByCategory(query);
    }

    private void getImageByCategory(String query) {
        ApiUtilities.getApiInterface().getSearchImage(query,1,80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                modelClassList.clear();
                if (response.isSuccessful()) {
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Not Able to get",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Not Able to get",Toast.LENGTH_SHORT).show();
            }
        });
    }
//
//    private void trending() {
//
//        modelClassList.clear();
//        ApiUtilities.getApiInterface().getImage(1,80).enqueue(new Callback<SearchModel>() {
//            @Override
//            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
//                if (response.isSuccessful()) {
//                    modelClassList.addAll(response.body().getPhotos());
//                    adapter.notifyDataSetChanged();
//                }
//                else {
//                    Toast.makeText(getApplicationContext(),"Not Able to get",Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SearchModel> call, Throwable t) {
//
//            }
//        });
//    }


}