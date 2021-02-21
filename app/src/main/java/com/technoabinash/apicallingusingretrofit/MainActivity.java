package com.technoabinash.apicallingusingretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.RemoteCallbackList;
import android.util.Log;
import android.widget.Toast;

import com.technoabinash.apicallingusingretrofit.adapter.PostAdapter;
import com.technoabinash.apicallingusingretrofit.apicall.ApiInterface;
import com.technoabinash.apicallingusingretrofit.apicall.RetrofitInstanse;
import com.technoabinash.apicallingusingretrofit.entities.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    RecyclerView recyclerView;
    PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.data_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        getData();

    }

    void getData() {
        apiInterface = RetrofitInstanse.getRetrofit().create(ApiInterface.class);
        apiInterface.getAllPost().enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if (response.body().size() > 0) {

                    adapter=new PostAdapter(response.body(),MainActivity.this);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(MainActivity.this, "list is empty", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Log.e("The main error", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }
}
