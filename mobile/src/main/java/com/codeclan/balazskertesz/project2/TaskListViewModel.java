package com.codeclan.balazskertesz.project2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class TaskListViewModel extends AndroidViewModel {

    //The main activity used this view model to refresh its data from the database
    //Also to manipulate any query or database object

    private final LiveData<List<Task>> taskList;

    private AppDatabase appDatabase;

    public TaskListViewModel(Application application){
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        taskList = appDatabase.taskDao().getAll();

    }

    public LiveData<List<Task>> getTaskList(){

        return taskList;
//        return appDatabase.taskDao().getRedPriority();
    }

    public void deleteItem(Task task){
        appDatabase.taskDao().deleteTask(task);
    }

    public void updateTask(Task task) {
        appDatabase.taskDao().updateTask(task);
    }




}
