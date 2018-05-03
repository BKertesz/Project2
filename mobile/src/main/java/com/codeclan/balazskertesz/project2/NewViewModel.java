package com.codeclan.balazskertesz.project2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

public class NewViewModel extends AndroidViewModel {
    //This is used to save new data to the database
    //The reason is why there is new ViewModel because there can be a chance if threaded
    //The main activity still using the other view Model
    //This would crash everything pretty heavily
    private AppDatabase appDatabase;

    public NewViewModel(Application application){
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());
    }

    public void addTask(final Task task){
        appDatabase.taskDao().insertTask(task);
    }
}
