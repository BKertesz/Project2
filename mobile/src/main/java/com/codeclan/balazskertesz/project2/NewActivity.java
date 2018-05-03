package com.codeclan.balazskertesz.project2;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {

    private EditText taskName;
    private EditText taskDescription;
    private RadioGroup taskPriority;

    private NewViewModel viewModel;

    private Button addTaskButton;
    private RadioButton priorityButton;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    finish();
                    return true;
                case R.id.navigation_new:

                    return true;
                case R.id.navigation_settings:
                    startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        taskName = (EditText) findViewById(R.id.newTaskNameId);
        taskDescription = (EditText) findViewById(R.id.taskNewDescriptionId);
        taskPriority = (RadioGroup) findViewById(R.id.newTaskProrityId);

        viewModel = ViewModelProviders.of(this).get(NewViewModel.class);

        addTaskButton = (Button) findViewById(R.id.buttonAddNewId);
        addTaskButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(taskName.getText().length() == 0 || taskPriority.getCheckedRadioButtonId() == -1){
                    Toast.makeText(NewActivity.this,"Missing fields", Toast.LENGTH_SHORT).show();
                }
                else if(taskName.getText() != null && taskPriority.getCheckedRadioButtonId() != -1){
                    if(taskDescription.getText() == null){taskDescription.setText("");}
                    RadioButton priority = findViewById(taskPriority.getCheckedRadioButtonId());
                    viewModel.addTask(new Task(
                            taskName.getText().toString(),
                            taskDescription.getText().toString(),
                            priority.getText().toString()
                    ));
                    finish();
                }

            }
        });

    }

    public void saveNewTask(View view){
        int i = 0;
//        priorityButton = findViewById(taskPriority.getCheckedRadioButtonId());
        if(taskName.getText() == null || taskDescription.getText() == null || taskPriority.getCheckedRadioButtonId() == -1 ){
            Toast.makeText(NewActivity.this, "Missing fields",Toast.LENGTH_SHORT).show();
        }
        else {
            priorityButton = findViewById(taskPriority.getCheckedRadioButtonId());
            viewModel.addTask(new Task(
                    taskName.getText().toString(),
                    taskDescription.getText().toString(),
                    priorityButton.getText().toString()
            ));
            finish();
        }

    }



}
