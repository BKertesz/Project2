package com.codeclan.balazskertesz.project2;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM tasks")
    LiveData<List<Task>> getAll();

    @Query("SELECT * FROM tasks WHERE taskId = (:taskId)")
    Task findById(int taskId);

    @Query("SELECT * FROM tasks WHERE name = (:name)")
    Task findByName(String name);

    @Insert
    void insertTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Update
    void updateTask(Task task);

    @Insert
    void insertAllTask(List<Task> tasks);

}
