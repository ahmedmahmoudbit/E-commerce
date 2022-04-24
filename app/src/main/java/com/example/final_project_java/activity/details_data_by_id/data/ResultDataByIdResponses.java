package com.example.final_project_java.activity.details_data_by_id.data;

import com.example.final_project_java.activity.activities.product.data.ProductData;
import com.google.gson.annotations.SerializedName;

public class ResultDataByIdResponses{

	@SerializedName("data")
	private ProductData data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(ProductData data){
		this.data = data;
	}

	public ProductData getData(){
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
			"ResultDataByIdResponses{" + 
			"data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}