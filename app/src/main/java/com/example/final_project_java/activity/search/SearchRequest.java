package com.example.final_project_java.activity.search;

import com.google.gson.annotations.SerializedName;

public class SearchRequest {
    @SerializedName("search-name")
    private String search_name;

    public String getSearch_name() {
        return search_name;
    }

    public void setSearch_name(String search_name) {
        this.search_name = search_name;
    }

    @Override
    public String toString() {
        return "SearchRequest{" +
                "search_name='" + search_name + '\'' +
                '}';
    }

}
