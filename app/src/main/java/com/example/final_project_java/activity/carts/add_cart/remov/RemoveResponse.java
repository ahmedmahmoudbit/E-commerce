package com.example.final_project_java.activity.carts.add_cart.remov;

import com.google.gson.annotations.SerializedName;

public class RemoveResponse {
    @SerializedName("data")
    private Object data;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public void setData(Object data){
        this.data = data;
    }

    public Object getData(){
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
                "RemoveResponse{" +
                        "data = '" + data + '\'' +
                        ",message = '" + message + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }

}
