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

public class ViewCounterActivity extends AppCompatActivity {

    Button btnEdit;
    Button btnBack;
    Button btnInc;
    Button btnDec;

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
    Type counterListType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_counter);

        initValues();
        Log.d("TETX", ""+ viewCounter);

        displayInformation();
    }

    @Override
    protected void onResume(){
        super.onResume();

        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }

        });

        btnEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent editCounterIntent = new Intent(getApplicationContext(), EditCounterActivity.class);
                startActivity(editCounterIntent);
            }

        });

    }

    public void initValues(){
        btnEdit = (Button) findViewById(R.id.vc_edit_button);
        btnBack = (Button) findViewById(R.id.vc_back_button);
//        btnDec;
//        btnInc;
        textName = (TextView) findViewById(R.id.vc_date_display);
        textDate = (TextView) findViewById(R.id.vc_curVal_display);
        textInitVal = (TextView) findViewById(R.id.vc_initVal_display);
        textCurVal = (TextView) findViewById(R.id.vc_curVal_display);
        textComment = (TextView) findViewById(R.id.vc_date_display);

        gson = new Gson();
        modifySharedPref = getSharedPreferences("counter", Context.MODE_PRIVATE);
        modifyEditor = modifySharedPref.edit();
        json = modifySharedPref.getString("counter","");
        counterListType = new TypeToken<Counter>() {}.getType();
        viewCounter = gson.fromJson(json, counterListType);

    }

    public void displayInformation(){
        textName.setText(viewCounter.getName());
        textDate.setText(viewCounter.getDate().toString());
        textInitVal.setText(String.format("%d",viewCounter.getInitValue()));
        textCurVal.setText(String.format("%d",viewCounter.getCurValue()));
        textComment.setText(viewCounter.getComment().toString());

    }
}
