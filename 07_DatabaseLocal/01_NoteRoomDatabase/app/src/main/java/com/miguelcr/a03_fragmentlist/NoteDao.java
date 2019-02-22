package com.miguelcr.a03_fragmentlist;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    // CRUD: Create, Read, Update, Delete

    // Insert a new note
    @Insert
    void insert(Note note);

    // Delete one note
    @Delete()
    void deleteNote(Note note);

    // Get all the notes
    @Query("SELECT * from notes")
    LiveData<List<Note>> getAllNotes();

    // update a note that exists
    @Update
    void update(Note note);

}
