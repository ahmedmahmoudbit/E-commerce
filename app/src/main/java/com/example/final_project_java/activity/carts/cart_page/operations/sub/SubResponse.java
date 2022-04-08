package com.example.final_project_java.activity.carts.cart_page.operations.sub;

import com.google.gson.annotations.SerializedName;

public class SubResponse {
    @SerializedName("data")
    private SubData data;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public void setData(SubData data){
        this.data = data;
    }

    public SubData getData(){
        return data;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public boolean isStatus(){
        return status;
    }

    @Override
    public String toString(){
        return
                "SubResponse{" +
                        "data = '" + data + '\'' +
                        ",message = '" + message + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }

}
