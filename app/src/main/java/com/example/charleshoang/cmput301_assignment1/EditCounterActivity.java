package com.example.charleshoang.cmput301_assignment1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class EditCounterActivity extends AppCompatActivity {

    /**
     * Variables that will be used in multple methods
     */

    Button btnEdit;
    Button btnBack;

    EditText editName;
    EditText editCount;
    EditText editComment;
    EditText editInitCount;

    Counter editCounter;
    Gson gson;
    String json;
    SharedPreferences modifySharedPref;
    SharedPreferences.Editor modifyEditor;
    Type counterType;

    AlertDialog.Builder builder;

    /**
     * On creation of the activity,
     * initialize variables and input current name, current value, and comment
     * into the fields.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_counter);

        initValues();
        inputOldText();

    }

    /**
     * Resume the activity
     */
    @Override
    protected void onResume(){
        super.onResume();

        /**
         * When button back is clicked, return to view activity.
         */
        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }

        });

        /**
         *  When button edit is clicked, change the counter's information to the edited inputs
         */


        btnEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                /**
                 * Check if both count and name are both filled. Otherwise, alert dialog.
                 */
                if (editName.getText().toString().equals("") || editCount.toString().equals("")){
                    missingBlankDialog();

                }
                /**
                 * Input the edit values into counter and put counter into shared preferences "counter"
                 *
                 */
                else{
                    editCounter.setName(editName.getText().toString());
                    editCounter.setComment(editComment.getText().toString());
                    editCounter.setCurValue(Integer.parseInt(editCount.getText().toString()));
                    editCounter.setInitValue(Integer.parseInt(editInitCount.getText().toString()));
                    modifySharedPref = getSharedPreferences("counter", Context.MODE_PRIVATE);
                    modifyEditor = modifySharedPref.edit();
                    modifyEditor.putString("counter", new Gson().toJson(editCounter));
                    modifyEditor.commit();

                    finish();
                }
            }

        });
    }

    /**
     * Method used to initialize variables.
     */

    public void initValues(){

        btnEdit = (Button) findViewById(R.id.ec_edit_button);
        btnBack = (Button) findViewById(R.id.ec_cancel_button);
        editName = (EditText) findViewById(R.id.ec_name_edit);
        editComment = (EditText) findViewById(R.id.ec_comment_edit);
        editCount = (EditText) findViewById(R.id.ec_count_edit);
        editInitCount = (EditText) findViewById(R.id.ec_intVal_edit);
        builder = new AlertDialog.Builder(this);

        /**
         * Retrieve counter to be modified from shared preferences "counter"
         */
        gson = new Gson();
        modifySharedPref = getSharedPreferences("counter", Context.MODE_PRIVATE);
        modifyEditor = modifySharedPref.edit();
        json = modifySharedPref.getString("counter","");
        counterType = new TypeToken<Counter>() {}.getType();
        editCounter = gson.fromJson(json, counterType);



    }

    /**
     * Method to print out the counters' information to blanks.
     */

    public void inputOldText(){
        editName.setText(editCounter.getName());

        editInitCount.setText(String.format("%d",editCounter.getInitValue()));

        editCount.setText(String.format("%d",editCounter.getCurValue()));

        editComment.setText(editCounter.getComment());

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
