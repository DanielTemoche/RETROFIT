package com.kytom.retrofit.Interface;

import com.kytom.retrofit.modelo.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("/products")

    Call<List<Post>> getPosts();
}
