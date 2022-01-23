package com.example.final_project_java.activity.search.data;

import com.google.gson.annotations.SerializedName;

public class SearchRequest {

    @SerializedName("search-name")
    private String name;

    public SearchRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SearchRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
