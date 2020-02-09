package com.android.retrofitapi.api;

import com.android.retrofitapi.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

interface Requests {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") int id);

    //    @Headers("Content-type: application/json; charset=UTF-8")
    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@Field("title") String title,
                            @Field("body") String body,
                            @Field("userId") int userId);

    @FormUrlEncoded
    @PUT("posts/{id}")
    Call<Post> updatePost(@Path("id") int id,
                            @Field("title") String title,
                            @Field("body") String body,
                            @Field("userId") int userId);

    @DELETE("posts/{id}")
    Call<Post> deletePost(@Path("id") int id);

}

