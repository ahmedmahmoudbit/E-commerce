package com.example.final_project_java.activity.search.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ColorItem implements Parcelable {

	@SerializedName("colorid")
	private String colorid;

	protected ColorItem(Parcel in) {
		colorid = in.readString();
	}

	public static final Creator<ColorItem> CREATOR = new Creator<ColorItem>() {
		@Override
		public ColorItem createFromParcel(Parcel in) {
			return new ColorItem(in);
		}

		@Override
		public ColorItem[] newArray(int size) {
			return new ColorItem[size];
		}
	};

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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(colorid);
	}
}