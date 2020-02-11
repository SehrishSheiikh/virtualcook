package com.sehrishsheikh.virtualcook.Login_As_Guest.Home;

public class Model
{
    String title , image , chef; //these names must match with the firebase database

    //create constructor

    public  Model()
    {

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getchef() {
        return chef;
    }

    public void setchef(String chef) {
        this.chef = chef;
    }
}
