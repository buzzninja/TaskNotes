package com.example.tasknotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class AddTaskActivity extends AppCompatActivity {

    private RadioButton radioButtonMediumPriority;
    private RadioButton radioButtonHighPriority;

    private EditText editTextAddTask;

    private Button buttonAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        initViews();

        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initViews(){
        radioButtonMediumPriority=findViewById(R.id.radioButtonMediumPriority);
        radioButtonHighPriority=findViewById(R.id.radioButtonHighPriority);

        editTextAddTask=findViewById(R.id.editTextAddTask);

        buttonAddTask=findViewById(R.id.buttonSaveTask);
    }
//Нажание на кнопку
    private void saveNote(){
        String taskText = editTextAddTask.getText().toString().trim();
        if (taskText.isEmpty()){
            Toast.makeText(getApplicationContext(),R.string.toastEmptyTask, Toast.LENGTH_SHORT).show();
        } else {
            int priority=getPriority();
                    }
    }
//Определение приоритета по нажатой кнопке
    private int getPriority(){
        int priority;
        if (radioButtonHighPriority.isChecked()) {
            priority=2;
        } else if (radioButtonMediumPriority.isChecked()) {
         priority=1;
     } else {
            priority=0;}
     return priority;
    }

    public  static Intent newIntent(Context context){
        return new Intent(context, AddTaskActivity.class);
    }
}