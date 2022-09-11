package com.example.tasknotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayoutNotes;
    private FloatingActionButton buttonAddNote;

    private Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();


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
        linearLayoutNotes = findViewById(R.id.linearLayoutNotes);
        buttonAddNote = findViewById(R.id.buttonAddNote);
    }

    private void showNotes(){
        linearLayoutNotes.removeAllViews();
        for (Note note:database.getNotes()){
            View view= getLayoutInflater().inflate(
                    R.layout.note_item,
                    linearLayoutNotes,
                    false
            );
            //удаление на нажатие
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    database.remove(note.getId());
                    showNotes();
                }
            });
            //установка текста заметки
            TextView textViewNotes = view.findViewById(R.id.textViewNote);
            textViewNotes.setText(note.getText());
            //установка цвета заметки
            int colorResId;
            switch (note.getPriority()){
                case 0:
                    colorResId= android.R.color.holo_green_light;
                    break;
                case 1:
                    colorResId= android.R.color.holo_orange_light;
                    break;
                default:
                    colorResId= android.R.color.holo_red_light;
                    break;

            }
            int color = ContextCompat.getColor(this,colorResId);
            textViewNotes.setBackgroundColor(color);
            //добавление заметок
            linearLayoutNotes.addView(view);
        }
    }
}