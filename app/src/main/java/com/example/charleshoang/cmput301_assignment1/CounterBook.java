package com.example.charleshoang.cmput301_assignment1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by charleshoang on 2017-09-30.
 * This class will be used to store the counters in an Array List
 */

public class CounterBook {

    /**
     * Array List to store Counters.
     */
    private ArrayList<Counter> counterBookList;

    /**
     * Constructs a new CounterBook with a List in the argument.
     */
    public CounterBook(){
        counterBookList = new ArrayList<>();
    }

    /**
     * Constructs a new CounterBook with an empty List.
     * @param counterBook
     */

    public CounterBook(ArrayList<Counter> counterBook) {
        this.counterBookList = counterBook;
    }

    /**
     * Returns the List for Counters.
     * @return
     */
    public List<Counter> getCounterBook() {
        return counterBookList;
    }

    /**
     * Sets the list to a inputted List.
     * @param counterBook
     */
    public void setCounterBook(ArrayList<Counter> counterBook) {
        this.counterBookList = counterBook;
    }

    /**
     * Returns true if they list is empty.
     * @return
     */
    public boolean isEmpty(){
        if (counterBookList.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Returns the size of the List.
     * @return
     */
    public int getSize(){
        return counterBookList.size();
    }


}
