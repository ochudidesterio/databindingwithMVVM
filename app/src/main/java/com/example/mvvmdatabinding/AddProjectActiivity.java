package com.example.mvvmdatabinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mvvmdatabinding.databinding.ActivityAddProjectActiivityBinding;
import com.example.mvvmdatabinding.entity.Project;
import com.example.mvvmdatabinding.viewmodels.ProjectViewModel;

public class AddProjectActiivity extends AppCompatActivity {
private ActivityAddProjectActiivityBinding mBinding;
private String title, language;
private int watcher,issues;
private ProjectViewModel mViewModel;
    private Project mProject;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityAddProjectActiivityBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setShowHideAnimationEnabled(true);

        if(getIntent().hasExtra("project")){
            mProject =getIntent().getParcelableExtra("project");
            mBinding.title.setText(mProject.title);
            mBinding.watcher.setText(String.valueOf(mProject.watcher));
            mBinding.issues.setText(String.valueOf(mProject.issues));
            isEdit = true;
        }


        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ProjectViewModel.class);

        mBinding.btnAdd.setOnClickListener((view)->{
            if(isEdit){
                title= mBinding.title.getText().toString().trim();
                language= mBinding.language.getText().toString().trim();
                watcher = Integer.parseInt(mBinding.watcher.getText().toString().trim());
                issues = Integer.parseInt(mBinding.issues.getText().toString().trim());

                mProject.title=title;
                mProject.language=language;
                mProject.watcher=watcher;
                mProject.issues=issues;
                /*
                *
                * *UPDATE PROJECT
                *
                 */
                mViewModel.update(mProject);
                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                finish();
            }else {
                /*

               *
               **IINSERT PROJECT
               *
                 */

                title= mBinding.title.getText().toString().trim();
                language= mBinding.language.getText().toString().trim();
                watcher = Integer.parseInt(mBinding.watcher.getText().toString().trim());
                issues = Integer.parseInt(mBinding.issues.getText().toString().trim());

                mProject = new Project();
                mProject.title=title;
                mProject.language=language;
                mProject.watcher=watcher;
                mProject.issues=issues;
                mViewModel.insert(mProject);

                Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();

                finish();
            }




        });
        //finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}