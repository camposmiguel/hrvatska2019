package com.miguelcr.a03_fragmentlist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository noteRepository;
    private LiveData<List<Note>> listAllNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        listAllNotes = noteRepository.getListAllNotes();
    }

    public LiveData<List<Note>> getListAllNotes() {
        return listAllNotes;
    }

    public void insert(Note note) {
        noteRepository.insert(note);
    }
}
