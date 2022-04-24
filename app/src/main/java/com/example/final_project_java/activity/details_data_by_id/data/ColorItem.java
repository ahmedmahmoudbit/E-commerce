package com.example.final_project_java.activity.details_data_by_id.data;

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