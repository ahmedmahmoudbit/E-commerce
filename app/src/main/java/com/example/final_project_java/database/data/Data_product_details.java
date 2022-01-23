package com.example.final_project_java.database.data;

public class Data_product_details {

    String brand , sku , condition , material , category , fitting ;

    public Data_product_details(String brand, String sku, String condition, String material, String category, String fitting) {
        this.brand = brand;
        this.sku = sku;
        this.condition = condition;
        this.material = material;
        this.category = category;
        this.fitting = fitting;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFitting() {
        return fitting;
    }

    public void setFitting(String fitting) {
        this.fitting = fitting;
    }
}
