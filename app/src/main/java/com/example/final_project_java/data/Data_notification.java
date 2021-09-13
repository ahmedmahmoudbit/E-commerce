package com.example.final_project_java.data;

public class Data_notification {
    String descripction , order , state , time;
    int img;

    public Data_notification(String descripction, String order, String state, String time, int img) {
        this.descripction = descripction;
        this.order = order;
        this.state = state;
        this.time = time;
        this.img = img;
    }

    public Data_notification(String descripction ,int img ) {
        this.descripction = descripction;
        this.img = img;
    }

    public String getDescripction() {
        return descripction;
    }

    public void setDescripction(String descripction) {
        this.descripction = descripction;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
