package com.example.final_project_java.activity.search.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImagesItem implements Parcelable {

	@SerializedName("image")
	private String image;

	@SerializedName("image_order")
	private String imageOrder;

	protected ImagesItem(Parcel in) {
		image = in.readString();
		imageOrder = in.readString();
	}

	public static final Creator<ImagesItem> CREATOR = new Creator<ImagesItem>() {
		@Override
		public ImagesItem createFromParcel(Parcel in) {
			return new ImagesItem(in);
		}

		@Override
		public ImagesItem[] newArray(int size) {
			return new ImagesItem[size];
		}
	};

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setImageOrder(String imageOrder){
		this.imageOrder = imageOrder;
	}

	public String getImageOrder(){
		return imageOrder;
	}

	@Override
 	public String toString(){
		return 
			"ImagesItem{" + 
			"image = '" + image + '\'' + 
			",image_order = '" + imageOrder + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(image);
		parcel.writeString(imageOrder);
	}
}