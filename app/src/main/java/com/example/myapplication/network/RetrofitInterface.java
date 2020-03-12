package com.example.myapplication.network;

import com.example.myapplication.models.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    @GET("/users")
    Call<List<UserResponse>> getUsers();
}
