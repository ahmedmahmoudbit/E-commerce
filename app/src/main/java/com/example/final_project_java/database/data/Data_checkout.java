package com.example.final_project_java.database.data;

public class Data_checkout {
    String product , details , price , count ;
    int img;

    public Data_checkout(String product, String details, String price, String count, int img) {
        this.product = product;
        this.details = details;
        this.price = price;
        this.count = count;
        this.img = img;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
