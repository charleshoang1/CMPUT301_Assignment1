package com.example.charleshoang.cmput301_assignment1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ViewCounterActivity extends AppCompatActivity {


    /**
     * Variables that will be used in multple methods
     */

    Button btnEdit;
    Button btnBack;
    Button btnInc;
    Button btnDec;
    Button btnDel;
    Button btnReset;

    TextView textName;
    TextView textDate;
    TextView textInitVal;
    TextView textCurVal;
    TextView textComment;

    Counter viewCounter;
    Gson gson;
    String json;
    SharedPreferences modifySharedPref;
    SharedPreferences.Editor modifyEditor;
    SharedPreferences bookSharedPref;
    SharedPreferences.Editor bookEditor;
    Type counterType;
    Type counterListType;

    int bookPos;
    CounterBook countBook;
    ArrayList<Counter> countList;

    boolean editedCounter;

    /**
     * When activity is created, initialize values and display the Counter's information
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_counter);

        initValues();
        displayInformation();
    }

    /**
     * While the Activity is running,
     * look for button clicks.
     */
    @Override
    protected void onResume(){
        super.onResume();

        /**
         * When the back button is clicked, return to MainActivity.
         */
        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                updateShared();
                finish();
            }

        });

        /**
         * When edit button is clicked, go to Edit activity.
         * Also, putting counter to be edited into sharedPreference
         */
        btnEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent editCounterIntent = new Intent(getApplicationContext(), EditCounterActivity.class);
                modifySharedPref = getSharedPreferences("counter", Context.MODE_PRIVATE);
                modifyEditor = modifySharedPref.edit();
                modifyEditor.putString("counter", new Gson().toJson(viewCounter));
                modifyEditor.commit();

                startActivity(editCounterIntent);
                /**
                 * boolean variable to see if a counter has been sent for editing.
                 */
                editedCounter = true;
            }

        });

        /**
         * When - button is clicked, decrement counter's current value.
         * Update value into counter and book.
         */

        btnDec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(viewCounter.getCurValue() <= 0) {
                    viewCounter.setCurValue(0);
                }
                else{
                    viewCounter.setCurValue(viewCounter.getCurValue()-1);
                    textCurVal.setText(String.format("%d",viewCounter.getCurValue()));
                }

                updateShared();
            }

        });

        /**
         * * When + button is clicked, increment counter's current value.
         * Update value into counter and book.
         */

        btnInc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                viewCounter.setCurValue(viewCounter.getCurValue()+1);
                textCurVal.setText(String.format("%d",viewCounter.getCurValue()));

                updateShared();
            }

        });


        btnReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                viewCounter.setCurValue(viewCounter.getInitValue());
                textCurVal.setText(String.format("%d",viewCounter.getCurValue()));
                updateShared();

            }

        });

        /**
         * When delete button is clicked, delete the counter.
         * Update book.
         * Return to MainActivity
         */
        btnDel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                textCurVal.setText(String.format("%d",viewCounter.getCurValue()));
                for (int i = 0; i < countBook.getSize(); i++){
                }
                countBook.getCounterBook().remove(bookPos);
                for (int i = 0; i < countBook.getSize(); i++){
                }

                bookSharedPref = getSharedPreferences("counterBook", Context.MODE_PRIVATE);
                bookEditor = bookSharedPref.edit();
                bookEditor.putString("counterBook", new Gson().toJson(countBook.getCounterBook()));

                bookEditor.commit();

                finish();

            }

        });

        updateShared();
        displayInformation();

    }

    /**
     * Initialize the variables
     */
    public void initValues(){

        /**
         * Widgets
         */
        btnEdit = (Button) findViewById(R.id.vc_edit_button);
        btnBack = (Button) findViewById(R.id.vc_back_button);
        btnDec = (Button) findViewById(R.id.vc_dec_button);
        btnInc = (Button) findViewById(R.id.vc_inc_button);
        btnReset = (Button) findViewById(R.id.vc_reset_button);
        btnDel = (Button) findViewById(R.id.vc_delete_button);

        textName = (TextView) findViewById(R.id.vc_name_display);
        textDate = (TextView) findViewById(R.id.vc_date_display);
        textInitVal = (TextView) findViewById(R.id.vc_initVal_display);
        textCurVal = (TextView) findViewById(R.id.vc_curVal_display);
        textComment = (TextView) findViewById(R.id.vc_comment_display);

        /**
         * Gson for sharing counter and Book
         */
        countBook = new CounterBook();
        gson = new Gson();
        modifySharedPref = getSharedPreferences("counter", Context.MODE_PRIVATE);
        modifyEditor = modifySharedPref.edit();
        json = modifySharedPref.getString("counter","");
        counterType = new TypeToken<Counter>() {}.getType();
        viewCounter = gson.fromJson(json, counterType);

        gson = new Gson();
        bookSharedPref = getSharedPreferences("counterBook", Context.MODE_PRIVATE);
        bookEditor = bookSharedPref.edit();
        editedCounter = false;

        counterListType = new TypeToken<ArrayList<Counter>>() {}.getType();
        String jsonBook =  bookSharedPref.getString("counterBook","");
        countList = gson.fromJson(jsonBook,counterListType);
        countBook.setCounterBook(countList );

        /**
         * Position of the counter in the book.
         */
        bookPos = modifySharedPref.getInt("position",0);

    }


    /**
     * Method to update the counter that has been edited and update into the Book.
     * If the counter was sent for Edit, input the edited Counter for sharing.
     */

    public void updateShared(){


        if (editedCounter == true){
            viewCounter = gson.fromJson(modifySharedPref.getString("counter",""), counterType);
            editedCounter = false;
        }

        modifySharedPref = getSharedPreferences("counter", Context.MODE_PRIVATE);
        modifyEditor = modifySharedPref.edit();
        modifyEditor.putString("counter", new Gson().toJson(viewCounter));
        modifyEditor.commit();

        bookSharedPref = getSharedPreferences("counterBook", Context.MODE_PRIVATE);
        bookEditor = bookSharedPref.edit();
        countBook.getCounterBook().set(bookPos, viewCounter);
        bookEditor.putString("counterBook", new Gson().toJson(countBook.getCounterBook()));

        bookEditor.commit();
        viewCounter = gson.fromJson(modifySharedPref.getString("counter",""), counterType);


    }

    /**
     * Display the counter's information in the located TextView
     */
    public void displayInformation(){
        textName.setText(viewCounter.getName());
        textDate.setText(viewCounter.getDate().toString());
        textInitVal.setText(String.format("%d",viewCounter.getInitValue()));
        textCurVal.setText(String.format("%d",viewCounter.getCurValue()));
        textComment.setText(viewCounter.getComment().toString());

    }
}
