package com.example.charleshoang.cmput301_assignment1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by charleshoang on 2017-09-30.
 * This will store the list of counters
 */

public class CounterBook {

    private List<Counter> counterBook;

    public CounterBook(){
        counterBook = new ArrayList<Counter>();
    }

    public CounterBook(List<Counter> counterBook) {
        this.counterBook = counterBook;
    }

    public List<Counter> getCounterBook() {
        return counterBook;
    }

    public boolean isEmpty(){
        if (counterBook.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }



}
