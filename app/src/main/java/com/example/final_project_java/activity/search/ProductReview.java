package com.example.final_project_java.activity.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductReview  implements Serializable {
    @SerializedName("rate_no")
    @Expose
    private String rateNo;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("creat_date")
    @Expose
    private String creatDate;
    @SerializedName("user")
    @Expose
    private User user;

    public String getRateNo() {
        return rateNo;
    }

    public void setRateNo(String rateNo) {
        this.rateNo = rateNo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
