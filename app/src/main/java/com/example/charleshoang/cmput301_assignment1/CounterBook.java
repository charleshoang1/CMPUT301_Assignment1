package com.example.charleshoang.cmput301_assignment1;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by charleshoang on 2017-09-30.
 * This will store the list of counters
 */

public class CounterBook {

    ArrayList<Counter> counterBookList;

    public CounterBook(){
        counterBookList = new ArrayList<>();
    }

    public CounterBook(ArrayList<Counter> counterBook) {
        this.counterBookList = counterBook;
    }

    public List<Counter> getCounterBook() {
        return counterBookList;
    }


    public void setCounterBook(ArrayList<Counter> counterBook) {
        this.counterBookList = counterBook;
    }

    public boolean isEmpty(){
        if (counterBookList.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    public int getSize(){
        return counterBookList.size();
    }

    public String[] listNames(){

        String[] names = new String[this.getSize()];
        for (int i = 0; i < this.getSize(); i++){
//            Log.d("ThisTag",""+ this.getSize());
//            Log.d("ThisTag","HERE" + this.getCounterBook().get(i).getName());
            names[i] = this.getCounterBook().get(i).getName();
        }
        Log.d("ThisTag","DONE" );
        return names;
    }

}
