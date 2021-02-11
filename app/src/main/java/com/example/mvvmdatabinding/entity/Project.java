package com.example.mvvmdatabinding.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "project")
public class Project implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String language;
    public int watcher;
    public int issues;

    public Project(String title, String language, int watcher, int issues) {
        this.title = title;
        this.language = language;
        this.watcher = watcher;
        this.issues = issues;
    }

    public Project(Parcel in) {
        id = in.readInt();
        title = in.readString();
        language = in.readString();
        watcher = in.readInt();
        issues = in.readInt();
    }

    public static final Creator<Project> CREATOR = new Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };

    public Project() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(language);
        dest.writeInt(watcher);
        dest.writeInt(issues);
    }
}
