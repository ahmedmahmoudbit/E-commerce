package com.example.final_project_java.activity.activities.product.data;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ProductResponse implements Serializable {

	@SerializedName("data")
	private List<ProductData> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(List<ProductData> data){
		this.data = data;
	}

	public List<ProductData> getData(){
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
			"ProductResponse{" + 
			"data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}