package com.example.final_project_java.activity.activities.product.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductImages implements Serializable {
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("image_order")
    @Expose
    private String imageOrder;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageOrder() {
        return imageOrder;
    }

    public void setImageOrder(String imageOrder) {
        this.imageOrder = imageOrder;
    }

}
