package com.miguelcr.a01_loginscreen.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.miguelcr.a01_loginscreen.AuthInterceptor;
import com.miguelcr.a01_loginscreen.MiniTwitterService;
import com.miguelcr.a01_loginscreen.R;
import com.miguelcr.a01_loginscreen.model.ResponseTweet;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TweetFragment extends Fragment {

    private int mColumnCount = 1;
    MyTweetRecyclerViewAdapter adapter;
    List<ResponseTweet> tweetList;
    RecyclerView recyclerView;

    public TweetFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tweet_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            getAllTweets();
        }
        return view;
    }

    private void getAllTweets() {
        // ******************* START ***********************
        // Include the header TOKEN in the request
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(new AuthInterceptor());
        OkHttpClient client = okHttpBuilder.build();

        // Connect to the server
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://minitwitter.com:3001/apiv1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        MiniTwitterService service = retrofit.create(MiniTwitterService.class);
        // ********************** END **********************

        Call<List<ResponseTweet>> call = service.getAllTweets();

        call.enqueue(new Callback<List<ResponseTweet>>() {
            @Override
            public void onResponse(Call<List<ResponseTweet>> call, Response<List<ResponseTweet>> response) {
                if(response.isSuccessful()) {
                    tweetList = response.body();

                    adapter = new MyTweetRecyclerViewAdapter(
                            getActivity(),
                            tweetList
                    );
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(),
                            "Sorry, try again!",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ResponseTweet>> call, Throwable t) {
                Toast.makeText(getActivity(),
                        "Houston, we've problems!",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }


}
