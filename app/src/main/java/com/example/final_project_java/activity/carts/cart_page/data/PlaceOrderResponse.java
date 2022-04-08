package com.example.final_project_java.activity.carts.cart_page.data;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PlaceOrderResponse{

	@SerializedName("data")
	private List<String> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(List<String> data){
		this.data = data;
	}

	public List<String> getData(){
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
			"PlaceOrderResponse{" + 
			"data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}