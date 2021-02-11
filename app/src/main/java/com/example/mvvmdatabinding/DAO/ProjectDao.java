package com.example.mvvmdatabinding.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mvvmdatabinding.entity.Project;

import java.util.List;

@Dao
public interface ProjectDao {
    @Insert
    void insert(Project project);
     @Update
    void update(Project project);
     @Delete
    void delete(Project project);
     @Query("SELECT * from project")
    LiveData<List<Project>>getAllProjects();


}
