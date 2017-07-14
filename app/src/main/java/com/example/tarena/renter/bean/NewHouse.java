package com.example.tarena.renter.bean;

import java.io.Serializable;

/**
 * Created by tarena on 2017/7/13.
 */

public class NewHouse implements Serializable {
    String avatar;
    String title;
    String address;
    String price;
    String perPrice;
    String detail;

    public String getPerPrice() {
        return perPrice;
    }

    public void setPerPrice(String perPrice) {
        this.perPrice = perPrice;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "NewHouse{" +
                "avatar='" + avatar + '\'' +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", price='" + price + '\'' +
                ", perPrice='" + perPrice + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
