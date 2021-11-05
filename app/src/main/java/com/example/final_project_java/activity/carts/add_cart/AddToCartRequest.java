package com.example.final_project_java.activity.carts.add_cart;

import com.google.gson.annotations.SerializedName;

public class AddToCartRequest {

    @SerializedName("size")
    String size;

    @SerializedName("color")
    String color;

    @SerializedName("product_id")
    String product_id;

    public AddToCartRequest(String size, String color, String product_id) {
        this.size = size;
        this.color = color;
        this.product_id = product_id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
