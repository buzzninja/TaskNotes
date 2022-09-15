package com.example.tasknotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes;
    private NotesAdapter notesAdapter;
    private FloatingActionButton buttonAddNote;


    private Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        notesAdapter = new NotesAdapter();
        //слушатель клика
        notesAdapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(Note note) {
                database.remove(note.getId());
                showNotes();
            }
        });

        recyclerViewNotes.setAdapter(notesAdapter);


        //Переход на AddTasActivity
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AddTaskActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        showNotes();
    }

    private void initViews(){
        buttonAddNote = findViewById(R.id.buttonAddNote);
        recyclerViewNotes=findViewById(R.id.recyclerViewNotes);
    }

    private void showNotes(){
        notesAdapter.setNotes(database.getNotes());
    }
}