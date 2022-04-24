package com.example.final_project_java.activity.search.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReviewItem implements Parcelable {

	@SerializedName("comment")
	private String comment;

	@SerializedName("creat_date")
	private String creatDate;

	@SerializedName("user")
	private User user;

	@SerializedName("rate_no")
	private String rateNo;

	protected ReviewItem(Parcel in) {
		comment = in.readString();
		creatDate = in.readString();
		rateNo = in.readString();
	}

	public static final Creator<ReviewItem> CREATOR = new Creator<ReviewItem>() {
		@Override
		public ReviewItem createFromParcel(Parcel in) {
			return new ReviewItem(in);
		}

		@Override
		public ReviewItem[] newArray(int size) {
			return new ReviewItem[size];
		}
	};

	public void setComment(String comment){
		this.comment = comment;
	}

	public String getComment(){
		return comment;
	}

	public void setCreatDate(String creatDate){
		this.creatDate = creatDate;
	}

	public String getCreatDate(){
		return creatDate;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setRateNo(String rateNo){
		this.rateNo = rateNo;
	}

	public String getRateNo(){
		return rateNo;
	}

	@Override
 	public String toString(){
		return 
			"ReviewItem{" + 
			"comment = '" + comment + '\'' + 
			",creat_date = '" + creatDate + '\'' + 
			",user = '" + user + '\'' + 
			",rate_no = '" + rateNo + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(comment);
		parcel.writeString(creatDate);
		parcel.writeString(rateNo);
	}
}