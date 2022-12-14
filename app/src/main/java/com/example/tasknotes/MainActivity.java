package com.example.tasknotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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

    private ArrayList<Note> notes=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        Random random=new Random();
        for (int i=0; i<20;i++){
        Note note=new Note(i, "Note "+i,random.nextInt(3));
        notes.add(note);
        }
        showNotes();
    }

    private void initViews(){
        linearLayoutNotes = findViewById(R.id.linearLayoutNotes);
        buttonAddNote = findViewById(R.id.buttonAddNote);
    }

    private void showNotes(){
        for (Note note:notes){
            View view= getLayoutInflater().inflate(
                    R.layout.note_item,
                    linearLayoutNotes,
                    false
            );
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