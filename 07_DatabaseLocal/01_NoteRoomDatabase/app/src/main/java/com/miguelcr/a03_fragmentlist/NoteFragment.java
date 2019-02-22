package com.miguelcr.a03_fragmentlist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miguelcr.a03_fragmentlist.dummy.DummyContent;
import com.miguelcr.a03_fragmentlist.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

public class NoteFragment extends Fragment {

    private int mColumnCount = 1;
    RecyclerView recyclerView;
    List<Note> noteList;
    MyNoteRecyclerViewAdapter adapter;
    NoteViewModel noteViewModel;

    public NoteFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        noteViewModel = ViewModelProviders
                .of(getActivity())
                .get(NoteViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;


            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            // Create the list of Notes
            noteViewModel.getListAllNotes().observe(
                    getActivity(),
                    new Observer<List<Note>>() {
                        @Override
                        public void onChanged(@Nullable List<Note> notes) {
                            adapter.setNotes(notes);
                        }
                    }
            );


            adapter = new MyNoteRecyclerViewAdapter(
                    getActivity(), // this
                    noteList
            );
            recyclerView.setAdapter(adapter);
        }
        return view;
    }



}
