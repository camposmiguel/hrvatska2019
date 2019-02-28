package com.miguelcr.a01_loginscreen;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        SharedPreferences sharedPref = MyApplication.getAppContext().getSharedPreferences("FILE", Context.MODE_PRIVATE);
        String token = sharedPref.getString("TOKEN",null);

        Request newRequest = chain.request().newBuilder().addHeader("Authorization", " Bearer "+ token).build();
        return chain.proceed(newRequest);
    }

}
