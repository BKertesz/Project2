package com.codeclan.balazskertesz.project2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class TaskListViewModel extends AndroidViewModel {

    private final LiveData<List<Task>> taskList;

    private AppDatabase appDatabase;

    public TaskListViewModel(Application application){
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        taskList = appDatabase.taskDao().getAll();

    }

    public LiveData<List<Task>> getTaskList(){
        return taskList;
    }

    public void deleteItem(Task task){
        appDatabase.taskDao().deleteTask(task);
    }

    public void addItem(Task task){
        appDatabase.taskDao().insertTask(task);
    }

}
