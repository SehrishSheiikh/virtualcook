package com.sehrishsheikh.massenger.Setting;

public class Setting_Model
{
    //(1)=>declaration

    String text;
    int img;

    public Setting_Model(String text , int img) {
        this.text = text;
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
