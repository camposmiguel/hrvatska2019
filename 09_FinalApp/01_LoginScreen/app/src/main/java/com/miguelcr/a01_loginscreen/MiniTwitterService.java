package com.miguelcr.a01_loginscreen;

import com.miguelcr.a01_loginscreen.model.RequestLogin;
import com.miguelcr.a01_loginscreen.model.RequestSignup;
import com.miguelcr.a01_loginscreen.model.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MiniTwitterService {

    @POST("auth/login")
    Call<ResponseLogin> doLogin(@Body RequestLogin requestLogin);

    @POST("auth/signup")
    Call<ResponseLogin> doSignup(@Body RequestSignup requestSignup);
}
