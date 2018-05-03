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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    List<Task> tasks;
    private RecyclerView recyclerView;
    private MyRecyclerAdapter adapter;
    public TaskListViewModel viewModel;
    public BottomNavigationView navigation;




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_new:
                    createNewTask();
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
        setContentView(R.layout.activity_main);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);

        //Find the recycleView on the main page
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewId);


        //Creates a new adapter instance and passes in the tasks
        final MyRecyclerAdapter adapter = new MyRecyclerAdapter(tasks,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Activates the recycleviewer with the adapter
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(TaskListViewModel.class);

        viewModel.getTaskList().observe(MainActivity.this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                adapter.addTasks(tasks);
            }
        });




    }



    public void createNewTask(){
        Intent getNewTask = new Intent(this,NewActivity.class);
        startActivity(getNewTask);
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigation.setSelectedItemId(R.id.navigation_home);
    }

    @Override
    public void onClick(View v) {

    }
}
