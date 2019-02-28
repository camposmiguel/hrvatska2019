package com.miguelcr.a01_loginscreen;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miguelcr.a01_loginscreen.dummy.DummyContent;
import com.miguelcr.a01_loginscreen.model.ResponseTweet;

import java.util.List;

public class TweetFragment extends Fragment {

    private int mColumnCount = 1;
    MyTweetRecyclerViewAdapter adapter;
    List<ResponseTweet> tweetList;

    public TweetFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tweet_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            adapter = new MyTweetRecyclerViewAdapter(
                    getActivity(),
                    tweetList
            );
            recyclerView.setAdapter(adapter);
        }
        return view;
    }



}
