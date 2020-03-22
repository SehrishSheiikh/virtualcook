package com.sehrishsheikh.massenger.People;

public class People_Model
{
    String title,day;
    int image;

    public People_Model(String title, String day, int image) {

        this.title = title;
        this.day = day;
        this.image = image;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}



