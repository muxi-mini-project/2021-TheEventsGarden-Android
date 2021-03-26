package com.example.myapplication.Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApi {
    @POST("user/")
    Call<LoginResponse> getCall(@Body User user);
}
