package com.example.charleshoang.cmput301_assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addCounterButton = (Button) findViewById(R.id.add_button);
        addCounterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent addCounterIntent = new Intent(MainActivity.this, CreateCounterActivity.class);
                startActivity(addCounterIntent);
            }

        });

    }

    public void sendMessage (View view){
        Intent startCreateCounter = new Intent (this, CreateCounterActivity.class);



    }
}
