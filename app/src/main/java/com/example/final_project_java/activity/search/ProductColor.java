package com.example.final_project_java.activity.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductColor {
    @SerializedName("colorid")
    @Expose
    private String colorid;

    public String getColorid() {
        return colorid;
    }

    public void setColorid(String colorid) {
        this.colorid = colorid;
    }

}
