package com.codeclan.balazskertesz.project2;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Task.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "task-database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }

        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }


    public abstract TaskDao taskDao();

}
