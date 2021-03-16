package com.github.novotnyr.yello

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun list(): LiveData<List<Note>>

    @Insert
    fun insert(note: Note)
}