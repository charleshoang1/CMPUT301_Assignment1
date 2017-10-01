package com.example.charleshoang.cmput301_assignment1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by charleshoang on 2017-09-30.
 */

class CreateCounterActivity extends AppCompatActivity {

    EditText counterName;
    EditText counterValue;
    EditText comment;
    Button create;
    Button cancel;
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_counter);

//        Button cancel = (Button)findViewById(R.id.create_cancel_button);
    }

}
