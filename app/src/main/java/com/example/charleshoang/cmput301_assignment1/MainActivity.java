package com.example.charleshoang.cmput301_assignment1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Charles Hoang 1394450
 * CMPUT 301 - Assignment 1
 *
 * Acknowledgements:
 * Gson usage with shared preferences - https://stackoverflow.com/questions/7145606/how-android-sharedpreferences-save-store-object
 * ArrayAdapter with 2 items - https://www.youtube.com/watch?v=QsO1_doWcak
 * Date Conversion to yyyy-MM-dd - https://stackoverflow.com/questions/226618/how-to-transform-a-time-value-into-yyyy-mm-dd-format-in-java
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Variables that will be used in multple methods
     */

    Button btnAdd;
    ListView counterView;
    TextView textEmptyBook;
    TextView textTotalCounter;

    CounterBook counterBook;
    Counter newCounter;
    Counter modifyCounter;
    ArrayList<Counter> arrayBook;
    Type counterListType;
    Type counterType;

    SharedPreferences bookSharedPref;
    SharedPreferences newCounterShare;
    SharedPreferences modifyCounterShare;
    SharedPreferences.Editor bookEditor;
    SharedPreferences.Editor newCounterEditor;
    SharedPreferences.Editor modifyEditor;

    Gson gson;
    String json;
    String jsonModify;

    boolean viewedCounter;
    int viewPosition;



    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    /**
     * On creation of activity, initialize variables.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVariables();

    }

    @Override
    /**
     * While the Activity is running, update the shared counter and book.
     * Main method for the actions for the activity.
     *
     */
    protected void onResume(){
        super.onResume();
        updateShared();

        /**
         * When add button is clicked, go to CreateCounterActivity.
         */
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent addCounterIntent = new Intent(getApplicationContext(), CreateCounterActivity.class);
                startActivity(addCounterIntent);
            }

        });

        /**
         * If the count is -1, lets the program know not to add a new counter.
         * Add counter to book and update book for sharing.
         */

        if (newCounterShare.getInt("value",0) != (-1)){
            newCounter = new Counter(newCounterShare.getString("name",""),
                    newCounterShare.getInt("value",0),newCounterShare.getString("comment",""));
            newCounterEditor.putInt("value", -1);
            newCounterEditor.putString("comment","");
            newCounterEditor.apply();

            counterBook.getCounterBook().add(newCounter);
            bookEditor.putString("counterBook", new Gson().toJson(counterBook.getCounterBook()));
            bookEditor.commit();
        }
        /**
         * Method for ListView counter click. Go to Edit Activity
         */
        counterView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent viewIntent = new Intent(getApplicationContext(), ViewCounterActivity.class);

                modifyCounterShare = getSharedPreferences("counter", Context.MODE_PRIVATE);
                modifyEditor = modifyCounterShare.edit();

                modifyEditor.putString("counter", new Gson().toJson(counterBook.getCounterBook().get(position)));
                modifyEditor.putInt("position", position);
                modifyEditor.commit();
                bookEditor.putString("counterBook", new Gson().toJson(counterBook.getCounterBook()));
                bookEditor.commit();
                startActivity(viewIntent);
                viewedCounter = true;
            }
        });

        /**
         * If the counter is being viewed/edited, retrieve counter
         */
        if (viewedCounter == true){
            modifyCounter = gson.fromJson(jsonModify, counterType);
            viewedCounter = false;
        }
        /**
         * If the countsize is > 0, display Listview. Hide emptyList TextView/
         */
        if (counterBook.getSize()>0){
            counterView.setVisibility(View.VISIBLE);
            textEmptyBook.setVisibility(View.INVISIBLE);
            textTotalCounter.setVisibility(View.VISIBLE);
            textTotalCounter.setText("Number of Counters: " + String.format("%d",counterBook.getSize()));
            displayListView();
        }
        /**
         * Display emptyLiset TextView when book is empty
         */
        else{
            counterView.setVisibility(View.INVISIBLE);
            textEmptyBook.setVisibility(View.VISIBLE);
            textTotalCounter.setVisibility(View.INVISIBLE);

        }

    }


    /**
     * Intialize Variables
     */
    private void initVariables() {

        counterBook = new CounterBook();
        btnAdd = (Button) findViewById(R.id.cb_add_button);
        textEmptyBook = (TextView) findViewById(R.id.cb_empty_book_text);
        textTotalCounter = (TextView) findViewById(R.id.cb_total_counter_text);
        counterView  = (ListView) findViewById(R.id.cb_counter_listview);

        bookSharedPref = getSharedPreferences("counterBook", Context.MODE_PRIVATE);
        bookEditor = bookSharedPref.edit();
        gson = new Gson();
        json = bookSharedPref.getString("counterBook",null);


        newCounterShare = getSharedPreferences("newCounter", Context.MODE_PRIVATE);
        newCounterEditor = newCounterShare.edit();

        modifyCounterShare = getSharedPreferences("counter", Context.MODE_PRIVATE);
        modifyEditor = modifyCounterShare.edit();
        jsonModify = modifyCounterShare.getString("counter","");
        counterType = new TypeToken<Counter>() {}.getType();
        counterListType = new TypeToken<ArrayList<Counter>>() {}.getType();
        viewPosition = modifyCounterShare.getInt("position",0);


        counterListType = new TypeToken<ArrayList<Counter>>() {}.getType();
        viewedCounter = false;

        arrayBook = (gson.fromJson(bookSharedPref.getString("counterBook",""), counterListType));
        counterBook.setCounterBook(arrayBook);

        //No CounterBook is in bookSharedPref. Use Gson to input a new empty ArrayList for sharing
        if (counterBook.getCounterBook() == null){

            counterBook.setCounterBook(new ArrayList<Counter>());
            bookEditor.putString("counterBook", new Gson().toJson(counterBook.getCounterBook()));
            bookEditor.commit();

            newCounterEditor.putInt("value",-1);;
            newCounterEditor.apply();


        }

        arrayBook = (gson.fromJson(json, counterListType));
        counterBook.setCounterBook(arrayBook);
    }

    /**
     * Display the list view into the screen
     */

    private void displayListView(){

        //Input counter's name and current value as strings
        final List<String[]> titleCountList = new ArrayList<String[]>();
        for (int i = 0; i < counterBook.getSize(); i++){
            String counterTitle = counterBook.getCounterBook().get(i).getName();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String countDate = dateFormat.format(counterBook.getCounterBook().get(i).getDate());
            String countInfo = "Date Created: " + countDate
                    + "\nCurrent Value: " + String.format("%d",counterBook.getCounterBook().get(i).getCurValue());
            titleCountList.add(new String[] {counterTitle,countInfo});

        }

        //Array adapter used to populate the list view and as 2 items per row.
        ArrayAdapter<String[]> counterDisplayAdapter = new ArrayAdapter<String[]>(this,
                android.R.layout.simple_list_item_2, android.R.id.text1,titleCountList){

            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);

                String[] listInputs =   titleCountList.get(position);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                text1.setText(listInputs[0]);
                text2.setText(listInputs[1]);

                return view;

            }
        };
        //Display the view
        counterView.setAdapter(counterDisplayAdapter);
    }

    /**
     * Keep up-to-date with the position of view, counter, and book
     */
    public void updateShared(){
        viewPosition = modifyCounterShare.getInt("position",0);
        modifyCounter = gson.fromJson(modifyCounterShare.getString("counter",""), counterType);
        arrayBook = (gson.fromJson(bookSharedPref.getString("counterBook",""), counterListType));
        counterBook.setCounterBook(arrayBook);


    }
}


