package com.example.mvvmdatabinding.ProjectRepository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mvvmdatabinding.DAO.ProjectDao;
import com.example.mvvmdatabinding.Database.ProjectDatabase;
import com.example.mvvmdatabinding.entity.Project;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Repository {

    ProjectDatabase mProjectDatabase;
   public ProjectDao mProjectDao;
   private Executor mExecutor = Executors.newSingleThreadExecutor();
   public Repository(Context context)
   {
       mProjectDatabase = ProjectDatabase.getInstance(context);
       mProjectDao = mProjectDatabase.mProjectDao();
   }
   public void insert(Project project){
       mExecutor.execute(new Runnable() {
           @Override
           public void run() {
               mProjectDao.insert(project);
           }
       });

   }
   public void update(Project project){
       mExecutor.execute(new Runnable() {
           @Override
           public void run() {
              mProjectDao.update(project);
           }
       });
   }
   public void delete(Project project){
       mExecutor.execute(new Runnable() {
           @Override
           public void run() {
               mProjectDao.delete(project);
           }
       });
   }
   public LiveData<List<Project>>getAllProjects(){
   return mProjectDao.getAllProjects();
   }
}
