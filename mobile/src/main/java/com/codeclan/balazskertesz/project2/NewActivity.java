package com.codeclan.balazskertesz.project2;

import android.arch.persistence.room.Room;
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

public class NewActivity extends AppCompatActivity {

    private EditText taskName;
    private EditText taskDescription;
    private RadioGroup taskPriority;

    private AppDatabase db;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_new:

                    return true;
                case R.id.navigation_settings:
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

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "task-database").build();
    }

    public void addNewButtonPressed(View view){
        Task newTask;

        String name = taskName.getText().toString();
        String description = taskDescription.getText().toString();

        RadioButton priorityButton = findViewById(taskPriority.getCheckedRadioButtonId());
        String priority = priorityButton.getText().toString();

        newTask = new Task(name,description,priority);

        db.taskDao().insertTask(newTask);

    }



}
