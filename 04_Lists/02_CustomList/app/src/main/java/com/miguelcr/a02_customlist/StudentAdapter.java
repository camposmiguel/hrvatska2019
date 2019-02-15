package com.miguelcr.a02_customlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {
    Context ctx;
    int layout;
    List<Student> values;

    public StudentAdapter(Context context, int resource, List<Student> objects) {
        super(context, resource, objects);
        this.ctx = context;
        this.layout = resource;
        this.values = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(ctx).inflate(
                layout,
                parent,
                false);

        // 1. Get the view components
        TextView tvName = v.findViewById(R.id.textViewName);
        TextView tvClassroom = v.findViewById(R.id.textViewClassroom);
        RatingBar rbRate = v.findViewById(R.id.ratingBarRate);
        ImageView ivPhoto = v.findViewById(R.id.imageViewPhoto);

        // 2. Get the current student
        Student current = values.get(position);
        String name = current.getName();
        String classroom = current.getClassroom();
        float rate = current.getRate();
        String photo = current.getPhoto();

        // 3. Set the student info into the View components
        tvName.setText(name);
        tvClassroom.setText(classroom);
        rbRate.setRating(rate);

        // Load image with Glide library: https://github.com/bumptech/glide
        Glide.with(ctx).load(photo).into(ivPhoto);

        return v;
    }
}
