package com.example.final_project_java.activity.carts.cart_page.showCartItems;

import com.google.gson.annotations.SerializedName;

public class UserId{

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
			"UserId{" + 
			"name = '" + name + '\'' + 
			"}";
		}
}