package com.example.final_project_java.activity.interfaces;

public interface ClickValue {
    // send product id and position .
    void onclickAdd(int productId , int position);
    void onclickSub(int productId , int position);
    void onclickRemove(int productId , int position);
}
