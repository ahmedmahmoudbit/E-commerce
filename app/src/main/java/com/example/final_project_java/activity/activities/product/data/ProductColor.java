package com.example.final_project_java.activity.activities.product.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductColor implements Serializable {

	@SerializedName("colorid")
	private String colorid;

	public void setColorid(String colorid){
		this.colorid = colorid;
	}

	public String getColorid(){
		return colorid;
	}

	@Override
 	public String toString(){
		return 
			"ColorItem{" + 
			"colorid = '" + colorid + '\'' + 
			"}";
		}
}