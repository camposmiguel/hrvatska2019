package com.miguelcr.a03_fragmentlist;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.miguelcr.a03_fragmentlist.dummy.DummyContent.DummyItem;

import java.util.List;


public class MyNoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder> {

    private final List<Note> mValues;
    private final Context context;

    public MyNoteRecyclerViewAdapter(Context ctx, List<Note> items) {
        context = ctx;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.tvTitle.setText(holder.mItem.getTitle());
        holder.tvContent.setText(holder.mItem.getContent());

        if(holder.mItem.isFavourite()) {
            holder.ivFav.setImageResource(R.drawable.ic_star_black_24dp);
        }

        holder.mView.setBackgroundColor(
                Color.parseColor(holder.mItem.getColor())
        );

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView ivFav;
        public final TextView tvTitle;
        public final TextView tvContent;
        public Note mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ivFav = view.findViewById(R.id.imageViewFav);
            tvTitle = view.findViewById(R.id.textViewTitle);
            tvContent = view.findViewById(R.id.textViewContent);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvTitle.getText() + "'";
        }
    }
}
