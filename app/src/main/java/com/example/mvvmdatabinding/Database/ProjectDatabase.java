package com.example.mvvmdatabinding.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mvvmdatabinding.DAO.ProjectDao;
import com.example.mvvmdatabinding.entity.Project;

@Database(entities = {Project.class},exportSchema = false,version = 1)
public abstract class ProjectDatabase extends RoomDatabase {

    public abstract ProjectDao mProjectDao();
    public static ProjectDatabase INSTANCE;
    public static final String DATABASE_NAME = "project_db";
    public static final Object LOCK = new Object();

    public static final ProjectDatabase getInstance(Context context){
        if(INSTANCE == null){
            synchronized (LOCK){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProjectDatabase.class,DATABASE_NAME)
                            .addCallback(sCallback)

                            .build();
                }
            }

        }
        return INSTANCE;
    }
    public static RoomDatabase.Callback sCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };
    public static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {
        private ProjectDao mProjectDao;
        private PopulateDbAsyncTask(ProjectDatabase projectDatabase){
            mProjectDao =projectDatabase.mProjectDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mProjectDao.insert(new Project("MVVM","Android",7,1));
            mProjectDao.insert(new Project("Caurotines","Kotlin",4,40));
            mProjectDao.insert(new Project("Lambda","Java",3,2));
            mProjectDao.insert(new Project("RxJava","Java",3,2));
            mProjectDao.insert(new Project("Layouts","Architecture",1,2));

            return null;
        }
    }
}
