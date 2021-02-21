package com.technoabinash.apicallingusingretrofit.apicall;

import com.technoabinash.apicallingusingretrofit.entities.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/posts")
    Call<List<Posts>> getAllPost();
}
