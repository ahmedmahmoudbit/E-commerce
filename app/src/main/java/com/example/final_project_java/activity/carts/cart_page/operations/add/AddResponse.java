package com.example.final_project_java.activity.carts.cart_page.operations.add;

import com.google.gson.annotations.SerializedName;

public class AddResponse {
    @SerializedName("data")
    private AddData data;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public void setData(AddData data){
        this.data = data;
    }

    public AddData getData(){
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
                "AddResponse{" +
                        "data = '" + data + '\'' +
                        ",message = '" + message + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }

}
