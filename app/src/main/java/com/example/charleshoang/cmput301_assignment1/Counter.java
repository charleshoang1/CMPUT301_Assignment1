package com.example.charleshoang.cmput301_assignment1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by charleshoang on 2017-09-30.
 * Class for Counter
 */

public class Counter{

    String name;
    int initValue;
    int curValue;
    Date date;
    String comment;

    public Counter(String name, int initValue) {
        this.name = name;
        this.initValue = initValue;

        date = new Date();
        curValue = initValue;
        comment = "";
    }

    public Counter(String name, int initValue, String comment) {
        this.name = name;
        this.initValue = initValue;

        date = new Date();
        curValue = initValue;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInitValue() {
        return initValue;
    }

    public int getCurValue() {
        return curValue;
    }

    public void setCurValue(int curValue) {
        this.curValue = curValue;
    }

    public Date getDate() {
        return date;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
