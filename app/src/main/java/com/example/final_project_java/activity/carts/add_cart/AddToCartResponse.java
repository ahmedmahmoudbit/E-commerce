package com.example.final_project_java.activity.carts.add_cart;

import com.google.gson.annotations.SerializedName;

public class AddToCartResponse{

	@SerializedName("data")
	private AddToCartData data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(AddToCartData data){
		this.data = data;
	}

	public AddToCartData getData(){
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
}