package com.example.contoh2.api;

import com.example.contoh2.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> auth(
            @Field("username") String userApi,
            @Field("api_key") String apiKey,
            @Field("user_id") String username,
            @Field("pass") String password);
}
