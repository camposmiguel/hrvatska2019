package com.miguelcr.a01_loginscreen.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.miguelcr.a01_loginscreen.R;
import com.miguelcr.a01_loginscreen.model.ResponseTweet;

import java.util.List;

public class MyTweetRecyclerViewAdapter extends RecyclerView.Adapter<MyTweetRecyclerViewAdapter.ViewHolder> {

    private final List<ResponseTweet> mValues;
    private Context ctx;

    public MyTweetRecyclerViewAdapter(Context context,
                                      List<ResponseTweet> items) {
        mValues = items;
        ctx = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tweet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.tvUsername.setText(holder.mItem.getUser().getUsername());
        holder.tvDate.setText(holder.mItem.getUser().getCreated());
        holder.tvMessage.setText(holder.mItem.getMensaje());

        if(holder.mItem.getLikes()!=null) {
            int numLikes = holder.mItem.getLikes().size();

            if(numLikes > 0) {
                holder.ivLikes.setImageResource(R.drawable.ic_like_pink);
                holder.tvLikes.setText(String.valueOf(numLikes));
            }
        }

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvUsername;
        public final ImageView ivLikes;
        public final TextView tvLikes;
        public final TextView tvMessage;
        public final TextView tvDate;
        public ResponseTweet mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvUsername = view.findViewById(R.id.textViewUsername);
            ivLikes = view.findViewById(R.id.imageViewLike);
            tvLikes = view.findViewById(R.id.textViewLike);
            tvMessage = view.findViewById(R.id.textViewMessage);
            tvDate = view.findViewById(R.id.textViewDate);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvUsername.getText() + "'";
        }
    }
}
