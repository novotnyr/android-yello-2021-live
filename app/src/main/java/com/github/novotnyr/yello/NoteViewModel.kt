package com.github.novotnyr.yello

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val appDatabase = AppDatabase(application)
    val noteDao = appDatabase.noteDao()

    fun getNotes(): LiveData<List<Note>> {
        return noteDao.list()
    }

    fun add(note: Note) {
        appDatabase.transactionExecutor.execute {
            noteDao.insert(note)
        }
    }
}