package com.example.mvvmdatabinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mvvmdatabinding.Adapters.ProjectAdapter;
import com.example.mvvmdatabinding.entity.Project;
import com.example.mvvmdatabinding.viewmodels.ProjectViewModel;
import com.example.mvvmdatabinding.databinding.ActivityMainBinding;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OclickItemInterface {
    private ActivityMainBinding mMainBinding;
    private ProjectAdapter mAdapter;
    private ProjectViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mMainBinding.getRoot());
        //setContentView(R.layout.activity_main);
        mMainBinding.projectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ProjectAdapter(this);
        mMainBinding.projectRecyclerView.setAdapter(mAdapter);

        mMainBinding.fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddProjectActiivity.class));
            }
        });

        mViewModel =ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ProjectViewModel.class);
        mViewModel.getAllProject().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(List<Project> projects) {
                mAdapter.setProjects(projects);
            }
        });

    }

    @Override
    public void onClick(Project project, boolean isEdit) {
        if(isEdit){
            Intent intent = new Intent(MainActivity.this,AddProjectActiivity.class);
            intent.putExtra("project",project);
            startActivity(intent);
        }else{
            mViewModel.delete(project);
        }
        Toast.makeText(this, ""+project.title, Toast.LENGTH_SHORT).show();
    }
}