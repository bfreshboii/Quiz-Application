package com.example.myquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.*;
import android.widget.*;

public class End extends AppCompatActivity {

    Button btnGoToQuiz, quit;
    TextView  receiver_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        btnGoToQuiz = findViewById(R.id.btnGoToQuiz);
        quit = findViewById(R.id.btnQuitProgram);

        receiver_msg = findViewById(R.id.txtWelcomeTxt);

        quit.setOnClickListener(quitListener);

        // create the get Intent object
        Intent intent = getIntent();
        // receive the value by getStringExtra() method and
        // key must be same which is send by first activity
        String str = intent.getStringExtra("Key");
        // display the string into textView
        receiver_msg.setText(str);

        btnGoToQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create the Intent object of this class Context() to Second_activity class
                Intent intent = new Intent(getApplicationContext(), Quiz.class);
                // now by putExtra method put the value in key, value pair key is
                // message_key by this key we will receive the value, and put the string
                intent.putExtra("key", "bundle");
                // start the Intent
                startActivity(intent);

            }
        });




    }

    private View.OnClickListener quitListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finishAffinity();
            System.exit(0);

        }
    };


}