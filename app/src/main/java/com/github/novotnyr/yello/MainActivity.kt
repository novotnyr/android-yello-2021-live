package com.github.novotnyr.yello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/*
0. [x] priprava zavislosti - library dependencies
        Android Room - objektovo relacne mapovanie pre objekty a databazy
1. [x] domenovy objekt / entita: poznamka Note

2. [x] diferencny objekt - DiffUtil
3. [x] ViewHolder - pre polozku zoznamu
4. [x] ListAdapter

5. ViewModel - niest data pre aktivitu - primarny zdroj pravdy pre zoznam "papierikov"

6. [x] objekt pre samotnu databazu - AppDatabase
7. [x] seria DAO - Data Access Object -- interfejsy s metodami pre pracu s objektami
 */

// a) [x] vyrobit viewModel
// b) [x] ziskat z neho zive data (LiveData) so zoznamom poznamok Note
// c) [x] na zive data nahodime posluchaca, ktory bude aktualizovat adapter

class MainActivity : AppCompatActivity() {
    val noteViewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val noteRecyclerView: RecyclerView = findViewById(R.id.noteRecyclerView)
        noteRecyclerView.layoutManager = LinearLayoutManager(this)

        NoteListAdapter().also {
            noteRecyclerView.adapter = it
            noteViewModel.getNotes().observe(this, it::submitList)
        }
    }

    fun onFabClick(view: View) {
        val descriptionEditText = EditText(this)

        AlertDialog.Builder(this)
            .setTitle("Zadajte text")
            .setMessage("Zadajte text na papieriku")
            .setView(descriptionEditText)
            .setPositiveButton("OK") { _, _ ->
                noteViewModel.add(Note(description = descriptionEditText.text.toString()))
            }
            .create()
            .show()
    }
}