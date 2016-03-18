package com.example.littledannyha.split;

import android.os.Bundle;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


public class SortActivity extends AppCompatActivity {

    // public static final String KEY = "com.as;dlfkj.aslkdjf.asldj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        int s = savedInstanceState.getInt(NumPeopleActivity.NUM_PEOPLE);
        setContentView(R.layout.activity_sort);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public void displayList() {
        int numRows = 5;

        int[] people = new int[5];

        for(int i = 0; i < people.length; i++) {
            //TextView text = new TextView("Person 1");
        }


    }

}
