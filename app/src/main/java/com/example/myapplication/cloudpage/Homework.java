package com.example.myapplication.cloudpage;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class Homework {
    private String teacher;
    private String title;
    private int id;
    private List<Homework> mHomeworkList;

    public Homework(Context context){
        mHomeworkList = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            Homework homework = new Homework(i);
            mHomeworkList.add(homework);
        }
    }

    public Homework(int id){
        this.id = id;
    }

    public List<Homework> getHomeworkList() {
        return mHomeworkList;
    }

    public void setHomeworkList(List<Homework> homeworkList) {
        mHomeworkList = homeworkList;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
