package com.example.final_project_java.activity.activities.product.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductDetail implements Serializable {

	@SerializedName("name")
	private String name;

	@SerializedName("desc")
	private String desc;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDesc(String desc){
		this.desc = desc;
	}

	public String getDesc(){
		return desc;
	}

	@Override
 	public String toString(){
		return 
			"DetailsItem{" + 
			"name = '" + name + '\'' + 
			",desc = '" + desc + '\'' + 
			"}";
		}
}