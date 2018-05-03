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


    //Handles the bottom status bar
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
        //Calls in all the buttons, and tex edit fields
        taskName = (EditText) findViewById(R.id.newTaskNameId);
        taskDescription = (EditText) findViewById(R.id.taskNewDescriptionId);
        taskPriority = (RadioGroup) findViewById(R.id.newTaskProrityId);

        //Gives acess to the viewModel and the database
        viewModel = ViewModelProviders.of(this).get(NewViewModel.class);

        addTaskButton = (Button) findViewById(R.id.buttonAddNewId);
        //Here is checks if the add text button is clicked or not
        addTaskButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Here it does some error handling
                //Returns a missing fields text if there is no name or priority selected
                if(taskName.getText().length() == 0 || taskPriority.getCheckedRadioButtonId() == -1){
                    Toast.makeText(NewActivity.this,"Missing fields", Toast.LENGTH_SHORT).show();
                }
                //Double check if everything is valid has name and has a priority
                //With an else can tricked into a bug with missing bits
                else if(taskName.getText() != null && taskPriority.getCheckedRadioButtonId() != -1){
                    //Description is optional, but it can't be null soo gets an empty value
                    if(taskDescription.getText() == null){taskDescription.setText("");}
                    //Checks which priority radio button is selected and pulls it value
                    RadioButton priority = findViewById(taskPriority.getCheckedRadioButtonId());
                    //First it creates a new task by using the fields and the radio button's value
                    //Then it is passed into the ViewModel's methods
                    //Behind the scenes this saves it to the database and updates the recycleview
                    viewModel.addTask(new Task(
                            taskName.getText().toString(),
                            taskDescription.getText().toString(),
                            priority.getText().toString()
                    ));
                    //Returns to the mainActivity when finished with the database save
                    finish();
                }

            }
        });

    }





}
