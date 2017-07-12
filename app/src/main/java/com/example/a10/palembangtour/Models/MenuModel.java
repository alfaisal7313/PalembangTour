package com.example.a10.palembangtour.Models;

/**
 * Created by 10 on 03/07/2017.
 */

public class MenuModel {

    private String title;
    private int image;

    public MenuModel(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
