package com.example.mvvmdatabinding.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmdatabinding.entity.Project;
import com.example.mvvmdatabinding.ProjectRepository.Repository;

import java.util.List;

public class ProjectViewModel extends AndroidViewModel {
    private Repository mRepository;

    public ProjectViewModel(@NonNull Application application) {
        super(application);
        mRepository =new  Repository(application);
    }
    public void insert(Project project){
        mRepository.insert(project);
    }
    public void update(Project project){
        mRepository.update(project);
    }
    public void delete(Project project){
        mRepository.delete(project);
    }
   public LiveData<List<Project>>getAllProject(){
       return mRepository.getAllProjects();
   }
}
