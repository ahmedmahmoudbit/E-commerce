package com.example.final_project_java.activity.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductData {
    @SerializedName("item_id")
    @Expose
    private Integer itemId;
    @SerializedName("item_name")
    @Expose
    private String itemName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("price_after_discount")
    @Expose
    private String priceAfterDiscount;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("item_image_url")
    @Expose
    private String itemImageUrl;
    @SerializedName("sizes")
    @Expose
    private List<ProductSize> sizes = null;
    @SerializedName("review")
    @Expose
    private List<ProductReview> review = null;
    @SerializedName("details")
    @Expose
    private List<ProductDetail> details = null;
    @SerializedName("color")
    @Expose
    private List<ProductColor> color = null;
    @SerializedName("images")
    @Expose
    private List<ProductImages> images = null;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(String priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getItemImageUrl() {
        return itemImageUrl;
    }

    public void setItemImageUrl(String itemImageUrl) {
        this.itemImageUrl = itemImageUrl;
    }

    public List<ProductSize> getSizes() {
        return sizes;
    }

    public void setSizes(List<ProductSize> sizes) {
        this.sizes = sizes;
    }

    public List<ProductReview> getReview() {
        return review;
    }

    public void setReview(List<ProductReview> review) {
        this.review = review;
    }

    public List<ProductDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ProductDetail> details) {
        this.details = details;
    }

    public List<ProductColor> getColor() {
        return color;
    }

    public void setColor(List<ProductColor> color) {
        this.color = color;
    }

    public List<ProductImages> getImages() {
        return images;
    }

    public void setImages(List<ProductImages> images) {
        this.images = images;
    }

}
