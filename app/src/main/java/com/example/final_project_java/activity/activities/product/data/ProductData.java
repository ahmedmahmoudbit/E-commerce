package com.example.final_project_java.activity.activities.product.data;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ProductData implements Serializable {

	@SerializedName("images")
	private List<ProductImages>  images;

	@SerializedName("item_image_url")
	private String itemImageUrl;

	@SerializedName("sizes")
	private List<ProductSize> sizes;

	@SerializedName("color")
	private List<ProductColor> color;

	@SerializedName("item_id")
	private int itemId;

	@SerializedName("price")
	private String price;

	@SerializedName("review")
	private List<ProductReview> review;

	@SerializedName("description")
	private String description;

	@SerializedName("discount")
	private String discount;

	@SerializedName("item_name")
	private String itemName;

	@SerializedName("details")
	private List<ProductDetail> details;

	@SerializedName("price_after_discount")
	private String priceAfterDiscount;

	public void setImages(List<ProductImages> images){
		this.images = images;
	}

	public List<ProductImages> getImages(){
		return images;
	}

	public void setItemImageUrl(String itemImageUrl){
		this.itemImageUrl = itemImageUrl;
	}

	public String getItemImageUrl(){
		return itemImageUrl;
	}

	public void setSizes(List<ProductSize> sizes){
		this.sizes = sizes;
	}

	public List<ProductSize> getSizes(){
		return sizes;
	}

	public void setColor(List<ProductColor> color){
		this.color = color;
	}

	public List<ProductColor> getColor(){
		return color;
	}

	public void setItemId(int itemId){
		this.itemId = itemId;
	}

	public int getItemId(){
		return itemId;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setReview(List<ProductReview> review){
		this.review = review;
	}

	public List<ProductReview> getReview(){
		return review;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setDiscount(String discount){
		this.discount = discount;
	}

	public String getDiscount(){
		return discount;
	}

	public void setItemName(String itemName){
		this.itemName = itemName;
	}

	public String getItemName(){
		return itemName;
	}

	public void setDetails(List<ProductDetail> details){
		this.details = details;
	}

	public List<ProductDetail> getDetails(){
		return details;
	}

	public void setPriceAfterDiscount(String priceAfterDiscount){
		this.priceAfterDiscount = priceAfterDiscount;
	}

	public String getPriceAfterDiscount(){
		return priceAfterDiscount;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"images = '" + images + '\'' + 
			",item_image_url = '" + itemImageUrl + '\'' + 
			",sizes = '" + sizes + '\'' + 
			",color = '" + color + '\'' + 
			",item_id = '" + itemId + '\'' + 
			",price = '" + price + '\'' + 
			",review = '" + review + '\'' + 
			",description = '" + description + '\'' + 
			",discount = '" + discount + '\'' + 
			",item_name = '" + itemName + '\'' + 
			",details = '" + details + '\'' + 
			",price_after_discount = '" + priceAfterDiscount + '\'' + 
			"}";
		}
}