package com.example.final_project_java.data;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Data_comments extends RecyclerView.ViewHolder {
    String name , comment , time;
    int photo , ratting;

    public Data_comments(@NonNull View itemView, String name, String comment, String time, int photo, int ratting) {
        super(itemView);
        this.name = name;
        this.comment = comment;
        this.time = time;
        this.photo = photo;
        this.ratting = ratting;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getRatting() {
        return ratting;
    }

    public void setRatting(int ratting) {
        this.ratting = ratting;
    }
}
