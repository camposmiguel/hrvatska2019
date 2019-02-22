package com.miguelcr.a03_fragmentlist;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class NoteRepository {

    public NoteDao noteDao;
    public LiveData<List<Note>> listAllNotes;

    public NoteRepository(Application application) {
        // Get a Database instance
        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);

        noteDao = db.noteDao();
        listAllNotes = noteDao.getAllNotes();
    }

    public LiveData<List<Note>> getListAllNotes() {
        return listAllNotes;
    }

    public void insert(Note note) {
        new insertAsyncTask(noteDao).execute(note);
    }

    private static class insertAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao mAsyncTaskDao;

        insertAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
