package com.example.charleshoang.cmput301_assignment1;

import java.util.Date;

/**
 * Created by charleshoang on 2017-09-30.
 * A class for Counter
 */

public class Counter{

    /**
     * Values
     */
    private String name;
    private int initValue;
    private int curValue;
    private Date date;
    private String comment;

    /**
     * Constructs the counter with name and initial value.
     * @param name
     * @param initValue
     */
    public Counter(String name, int initValue) {
        this.name = name;
        this.initValue = initValue;

        date = new Date();
        curValue = initValue;
        comment = "";
    }

    /**
     * Construct the counter with nane, initial value, and commment.
     * @param name
     * @param initValue
     * @param comment
     */

    public Counter(String name, int initValue, String comment) {
        this.name = name;
        this.initValue = initValue;

        date = new Date();
        curValue = initValue;
        this.comment = comment;
    }

    /**
     * Returns the name of the counter.
     * @return
     */

    public String getName() {
        return name;
    }

    /**
     * Sets the name for the counter.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the initial value.
     * @return
     */

    public int getInitValue() {
        return initValue;
    }

    /**
     * Returns the current value.
     * @return
     */

    public int getCurValue() {
        return curValue;
    }

    /**
     * Sets the current value to the new inputted value.
     * @param curValue
     */

    public void setCurValue(int curValue) {
        this.curValue = curValue;
    }

    /**
     * Returns the date that the counter was created.
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the comment.
     * @return
     */

    public String getComment() {
        return comment;
    }

    public void setInitValue(int initValue) {
        this.initValue = initValue;
    }

    /**
     * Sets the inputted string as the new comment.

     * @param comment
     */

    public void setComment(String comment) {
        this.comment = comment;
    }


}
