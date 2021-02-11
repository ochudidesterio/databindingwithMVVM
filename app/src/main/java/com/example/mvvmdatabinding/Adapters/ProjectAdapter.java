package com.example.mvvmdatabinding.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmdatabinding.OclickItemInterface;
import com.example.mvvmdatabinding.entity.Project;
import com.example.mvvmdatabinding.databinding.RecyclerItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {
   //Context mContext;
    private RecyclerItemBinding mBinding;
    private List<Project>mProjects = new ArrayList<>();
    private  OclickItemInterface mOclickItemInterface;

    public ProjectAdapter(OclickItemInterface oclickItemInterface) {
        mOclickItemInterface = oclickItemInterface;
    }
    //    public ProjectAdapter(Context context, List<Project>mProject) {
//        mContext = context;
//        mProjects= mProject;
//    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mBinding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new  ProjectViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        Project current = mProjects.get(position);
        mBinding.setProject(current);
        mBinding.textTitle.setText(current.title);
        mBinding.textIssues.setText(Integer.toString(current.issues));

        mBinding.setListener(mOclickItemInterface);



    }

    @Override
    public int getItemCount() {
        return mProjects.size();
    }
    public void setProjects(List<Project>projects){
        mProjects = projects;
        notifyDataSetChanged();
    }

    public class ProjectViewHolder extends RecyclerView.ViewHolder{

        public ProjectViewHolder(@NonNull RecyclerItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
