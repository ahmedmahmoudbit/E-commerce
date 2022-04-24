package com.example.final_project_java.activity.search.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SizesItem implements Parcelable {

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	protected SizesItem(Parcel in) {
		name = in.readString();
		id = in.readInt();
	}

	public static final Creator<SizesItem> CREATOR = new Creator<SizesItem>() {
		@Override
		public SizesItem createFromParcel(Parcel in) {
			return new SizesItem(in);
		}

		@Override
		public SizesItem[] newArray(int size) {
			return new SizesItem[size];
		}
	};

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"SizesItem{" + 
			"name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(name);
		parcel.writeInt(id);
	}
}