package com.example.final_project_java.activity.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetail {
    public class Detail {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("desc")
        @Expose
        private String desc;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

    }
}
