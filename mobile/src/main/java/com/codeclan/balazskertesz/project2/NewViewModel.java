package com.codeclan.balazskertesz.project2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

public class NewViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public NewViewModel(Application application){
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());
    }

    public void addTask(final Task task){
        appDatabase.taskDao().insertTask(task);
    }
}
