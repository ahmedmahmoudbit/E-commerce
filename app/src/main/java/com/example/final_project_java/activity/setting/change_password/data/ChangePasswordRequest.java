package com.example.final_project_java.activity.setting.change_password.data;

import com.google.gson.annotations.SerializedName;

public class ChangePasswordRequest {

    @SerializedName("new_password")
    private String new_password;

    @SerializedName("confirm_password")
    private String confirm_password;

    @SerializedName("old_password")
    private String old_password;

    public ChangePasswordRequest(String new_password, String confirm_password, String old_password) {
        this.new_password = new_password;
        this.confirm_password = confirm_password;
        this.old_password = old_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }
}
