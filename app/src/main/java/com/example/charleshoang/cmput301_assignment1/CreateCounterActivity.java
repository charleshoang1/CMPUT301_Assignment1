package com.example.charleshoang.cmput301_assignment1;

import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by charleshoang on 2017-09-30.
 *
 * This activity is used to create a new counter.
 */

public class CreateCounterActivity extends AppCompatActivity {

    /**
     * Variables that will be used in multple methods
     */
    EditText counterName;
    EditText counterValue;
    EditText counterComment;
    Button btnCreate;
    Button btnCancel;


    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    AlertDialog.Builder builder;

    Counter counter;
    CounterBook cB;


    /**
     * When the activity is created, initialize the variables.
     * Create the counter
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_counter);

        initVariables();

        createCounter();

    }

    /**
     * Method used to initialize variables.
     */
    private void initVariables(){
        btnCancel = (Button)findViewById(R.id.cc_cancel_button);
        btnCreate = (Button)findViewById(R.id.cc_create_button);
        counterName = (EditText)findViewById(R.id.cc_name_edit);
        counterValue = (EditText)findViewById(R.id.cc_count_edit);
        counterComment = (EditText)findViewById(R.id.cc_comment_edit);
        builder = new AlertDialog.Builder(this);
        sharedPref = getSharedPreferences("newCounter", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    /**
     * Method to create the new counter and use shared preferences to
     * share the new count book list.
     *
     */

    public void createCounter(){

        /**
         * To handle when the create button is clicked
         */
        btnCreate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                /**
                 * Check if both count and name are both filled. Otherwise, alert dialog.
                 */
                if (counterName.getText().toString().equals("") || counterValue.getText().toString().equals("")){
                    missingBlankDialog();
                }

                /**
                 * Input the name, initial count, and comment into the editor shared preference
                 */
                else{

                    editor.putString("name", counterName.getText().toString());
                    editor.putInt("value",Integer.parseInt(counterValue.getText().toString()));
                    editor.putString("comment", counterComment.getText().toString());
                    editor.apply();
                    finish();
                }
            }
        });

        /**
         * If the button cancelled is click, return to the MainActivity.
         * The value -1, is to let the MainActivity know not to add a new counter.
         */
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                editor.putInt("value", -1);
                editor.apply();
                finish();

            }
        });
    }

    /**
     * Method to display a alert dialog.
     * Notify user to fill out missing required blanks.
     */

    public void missingBlankDialog() {
        builder.setMessage("Blanks with * must be filled out.")
                .setNegativeButton("Return", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        ;
                    }
                })
                .setTitle("Missing Blanks");

        builder.show();
    }
}
