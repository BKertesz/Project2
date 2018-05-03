package com.codeclan.balazskertesz.project2;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

//This class is used for access to the SQL database via the Room Library

//Here you define this class is a database and takes in one tables bases on the class
//The version number refers and need to be changed if you are changing your table structure
//But want to keep the old ones aswell
@Database(entities = {Task.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    //The instance is used for error checking and making sure you have access to database

    public static AppDatabase getDatabase(Context context){
        //Check if there is already an open database
        //If it is not open then open it and let other apps use the instance
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "task-database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }

        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }
    //This method is for forcefully close the database connection


    //This part is used to reference the Data Access Object
    public abstract TaskDao taskDao();

}
