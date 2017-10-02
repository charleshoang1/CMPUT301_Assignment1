package com.example.charleshoang.cmput301_assignment1;

import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

/**
 * Created by charleshoang on 2017-09-30.
 */

public class CreateCounterActivity extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_counter);

        initVariables();

        createCounter();

    }

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

    public void createCounter(){

        btnCreate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){


                if (counterName.getText().toString().equals("") || counterValue.getText().toString().equals("")){
                    missingBlankDialog();
                }
                else{

//                    counter = new Counter(counterName.getText().toString(), Integer.parseInt(counterValue.getText().toString())
//                            ,counterComment.getText().toString());
                    editor.putString("name", counterName.getText().toString());
                    editor.putInt("value",Integer.parseInt(counterValue.getText().toString()));
                    editor.putString("comment", counterComment.getText().toString());
                    editor.apply();
                    Log.d("MYtAG", "name: " + sharedPref.getString("name","") + " " + "value: " + sharedPref.getInt("value",0)+ " " + "comment: " +sharedPref.getString("comment",""));



                    finish();


                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                editor.putInt("value", -1);
                editor.apply();
                Log.d("MY TAG", "CANCEL WAS PRESSED IN CREATEACTIV");

                finish();

            }
        });



    }

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
