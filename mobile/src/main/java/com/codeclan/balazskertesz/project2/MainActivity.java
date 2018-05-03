package com.codeclan.balazskertesz.project2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Reference for everything used by main activity
    List<Task> tasks;
    private RecyclerView recyclerView;
    public TaskListViewModel viewModel;
    public BottomNavigationView navigation;




    //This is the built in navigation bar listener for the bottom
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_new:
                    //Here it starts the activity for adding new task
                    createNewTask();
                    return true;
                case R.id.navigation_settings:
                    //This actually creates an intent outside the app for the settings
                    //Built into android
                    startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //Sets up what is seleted on the start of the app
        navigation.setSelectedItemId(R.id.navigation_home);

        //Find the recycleView on the main page
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewId);


        //Creates a new adapter instance and passes in the tasks
        //Note that task is actually empty and not used anywhere else
        //This is a bug that the adapter can't be initialized without something in it
        final MyRecyclerAdapter adapter = new MyRecyclerAdapter(tasks);

        //This bit selects how I want to display the rows on the activity
        //The three selection is Linear, Grid, Staggered Grid layout
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //Activates the RecyclerView with the adapter
        recyclerView.setAdapter(adapter);

        //The viewModel is used to give a live access to the database
        viewModel = ViewModelProviders.of(this).get(TaskListViewModel.class);

        //Here the viewModel updates the adapter's task list contionusly
        viewModel.getTaskList().observe(MainActivity.this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                adapter.addTasks(tasks);
            }
        });




    }



    public void createNewTask(){
        //This is used by the bottomnavigation bar to go to the newTask activity
        Intent getNewTask = new Intent(this,NewActivity.class);
        startActivity(getNewTask);
    }


    @Override
    protected void onResume() {
        super.onResume();
        //Every time we return to the activity make sure the Home button is highlighted
        //On the bottomNavigationBar
        navigation.setSelectedItemId(R.id.navigation_home);
    }


}
