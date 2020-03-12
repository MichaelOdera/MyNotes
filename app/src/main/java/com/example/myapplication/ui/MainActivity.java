package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.adapter.RecyclerViewCustomAdapter;
import com.example.myapplication.network.RetrofitInstanceClient;
import com.example.myapplication.network.RetrofitInterface;
import com.example.myapplication.models.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView userListRecyclerView;
    private RetrofitInterface retrofitInstanceClient;
    private List<UserResponse> mUsers;
    private RecyclerViewCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchDataFromServer();

    }

    private void fetchDataFromServer() {
        retrofitInstanceClient = new RetrofitInstanceClient().getRetrofit().create(RetrofitInterface.class);
        Call<List<UserResponse>> call = retrofitInstanceClient.getUsers();
        call.enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                //the response
                Log.d("Response :", "Success");
                if(response.code() == 200){
                mUsers = response.body();
                initializeDisplay();
                }

            }


            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {

            }

        });


    }
    private void initializeDisplay() {
        userListRecyclerView = findViewById(R.id.list_users);
        adapter = new RecyclerViewCustomAdapter(this, mUsers);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        userListRecyclerView.setAdapter(adapter);
        userListRecyclerView.setLayoutManager(layoutManager);
    }
}
