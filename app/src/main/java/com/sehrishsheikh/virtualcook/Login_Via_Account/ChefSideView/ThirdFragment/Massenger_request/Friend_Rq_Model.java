package com.sehrishsheikh.massenger.Massenger_request;

public class Friend_Rq_Model
{

    String title, description,day;
    int  dp ;

    public Friend_Rq_Model(String title, String description, String day, int dp)
    {
        this.title = title;
        this.description = description;
        this.day = day;
        this.dp = dp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }





    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getDp() {
        return dp;
    }

    public void setDp(int dp) {
        this.dp = dp;
    }
}

