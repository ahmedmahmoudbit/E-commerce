package com.example.final_project_java.activity.carts.cart_page.showCartItems;

import com.google.gson.annotations.SerializedName;

public class ColorItem{

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