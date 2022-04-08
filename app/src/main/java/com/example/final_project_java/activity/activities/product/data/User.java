package com.example.final_project_java.activity.activities.product.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

	@SerializedName("name")
	private String name;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"name = '" + name + '\'' + 
			"}";
		}
}