package com.example.littledannyha.split;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.content.Intent;

public class NumPeopleActivity extends AppCompatActivity {

    public static final String NUM_PEOPLE = "com.Split.MainActivity.numberPeople";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_people);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        next();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public int getNumPeople() {
        // Based on input from the user, return the same integer
        Log.d("getNumPeople", "cp0");
        EditText text = (EditText) findViewById(R.id.num_people);
        Log.d("getNumPeople", "cp1");
        String output = text.getText().toString();
        Log.d("getNumPeople", "cp2");
        Log.d("output: ", output);

        int num = Integer.parseInt(output);
        Log.d("getNumPeople", "successfully returned");
        return num;
    }

    public double getTipPercentage(EditText text) {
        // Based on input from the user, return the same double
        text = (EditText) findViewById(R.id.tip_amount);
        String output = text.toString();
        double tip = Double.parseDouble(output);
        return tip;
    }

    public void next() {
        android.widget.Button button = (android.widget.Button) findViewById(R.id.next_button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NumPeopleActivity.this, SortActivity.class);
                startActivity(intent);
                intent.putExtra(NUM_PEOPLE, getNumPeople());
            }
        });


    }
}
