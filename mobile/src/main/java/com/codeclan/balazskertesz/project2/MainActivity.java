package com.codeclan.balazskertesz.project2;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final int NEW_TASK_CODE = 1;

    List<Task> tasks;


    private AppDatabase db;

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
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "task-database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewId);

        tasks = db.taskDao().getAll();

        MyRecyclerAdapter adapter = new MyRecyclerAdapter(tasks);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data){
        if(requestCode == NEW_TASK_CODE){

            if(resultCode== RESULT_OK){
                String name = data.getStringExtra("name");
                String description = data.getStringExtra("description");
                String priority = data.getStringExtra("priority");

                Task task = new Task(name,description,priority);
                db.taskDao().insertTask(task);
            }

        }

    }

    public void createNewTask(){
        Intent getNewTask = new Intent(this,NewActivity.class);
        startActivityForResult(getNewTask,NEW_TASK_CODE);
    }





}
