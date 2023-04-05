package com.example.myquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import java.io.*;
import java.util.ArrayList;
import android.view.*;
import android.widget.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Quiz extends AppCompatActivity {

    //Declaring my variables
    int currentIndex = 1;

    double score = 0;

    String selectedAns = "";

    String answer,str;

    String thisQuestion;

    //create the lists
    ArrayList<String> listDef;

    ArrayList<String> listTerm;


    //Declaring the text views and buttons
    TextView questionTotal;

    TextView question,questionCounter;

    Button ansA,
            ansB,
            ansC,
            ansD;

    Button results;


    //Declaring the hash map
    Map<String,String> map = new HashMap<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Finding the ids for my text views and buttons
        questionTotal = findViewById(R.id.QuizApp);
        question = findViewById(R.id.txtQuestions);
        questionCounter = findViewById(R.id.txtQuestionCount);
        ansA = findViewById(R.id.answerA);
        ansB = findViewById(R.id.answerA2);
        ansC = findViewById(R.id.answerA3);
        ansD = findViewById(R.id.answerA4);
        results = findViewById(R.id.btnResults);



        //Setting the listeners
        ansA.setOnClickListener(ansAListener);
        ansB.setOnClickListener(ansBListener);
        ansC.setOnClickListener(ansCListener);
        ansD.setOnClickListener(ansDListener);
        results.setOnClickListener(resultListener);




        //reading the file
        readFile();

        //Using a for loop to map the definitions to the terms
        for (int i = 0; i < listDef.size(); i++) {

            map.put(listDef.get(i), listTerm.get(i));

        }

        loadNewQuestion();






    }






    private void readFile(){

        listDef = new ArrayList<>();//Sets both lists to 0
        listTerm = new ArrayList<>();

        //test the read from file
        String str = null;
        BufferedReader br = null;


        try {//wrapping the read in a try catch

            InputStream is = getResources().openRawResource(R.raw.text);

            br = new BufferedReader(new InputStreamReader(is));

            System.out.println("File in RAW is open");//Just to note that the file is opened

            String term, def;//Declaring the string

            //While loop to do the reading
            while ((str = br.readLine()) != null) {

                String[] result = str.split("\\$");//Using the .split() function with the regex

                //Setting the terms and delimiters accordingly
                term = result[1];
                def = result[0];


                //Adding them to the list
                listTerm.add(term);
                listDef.add(def);


            }
            //Printing to show that they were added correctly


            //Closing the file and printing a message
            is.close();
            System.out.println("File in RAW is closed");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Setting the text for the textview
        questionCounter.setText(currentIndex +"/"+listDef.size());




    }

    //Creating the button listeners

    private View.OnClickListener ansAListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String correctAnswer = ansA.getText().toString();

            answer = map.get(question.getText().toString());//Using the hash map to set the correct answer

            primeQuestion();

            if(correctAnswer.equals(answer)){

                score++;//increase score




            }





        }
    };

    private View.OnClickListener ansBListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String correctAnswer = ansB.getText().toString();

            answer = map.get(question.getText().toString());//Using the hash map to set the correct answer

            primeQuestion();


            if(correctAnswer.equals(answer)){

                score++;//increase score




            }



        }
    };
    private View.OnClickListener ansCListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String correctAnswer = ansC.getText().toString();

            answer = map.get(question.getText().toString());//Using the hash map to set the correct answer

            primeQuestion();

            if(correctAnswer.equals(answer)){

                score++;//increase score




            }



        }
    };
    private View.OnClickListener ansDListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String correctAnswer = ansD.getText().toString();

            answer = map.get(question.getText().toString());//Using the hash map to set the correct answer

            primeQuestion();

            if(correctAnswer.equals(answer)){

                score++;//increase score




            }



        }
    };

    private View.OnClickListener resultListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            loadNewQuestion();


            if(currentIndex == listTerm.size()){
                currentIndex -=1;
            }

            if((score/listTerm.size()) < 0.60){

                questionCounter.setTextColor(Color.parseColor("Red"));


            }else{


                questionCounter.setTextColor(Color.parseColor("Green"));

            }

            questionCounter.setText(Integer.toString(currentIndex+=1) + "/" + listTerm.size());




        }
    };


//    public void onClick(View view) {
//
//        //Setting the button background colors to grey
//        ansA.setBackgroundColor(Color.DKGRAY);
//        ansB.setBackgroundColor(Color.DKGRAY);
//        ansC.setBackgroundColor(Color.DKGRAY);
//        ansD.setBackgroundColor(Color.DKGRAY);
//
//
//
//        //Setting a button clicked view
//
////            //This sets the selected answer to blue
////            selectedAns = clickedButton.getText().toString();
////            clickedButton.setBackgroundColor(Color.BLUE);
//
//
//
//
//    }

    //Creating the functions

    void primeQuestion(){


        ansA.setBackgroundColor(Color.parseColor("Red"));
        ansB.setBackgroundColor(Color.parseColor("Red"));
        ansC.setBackgroundColor(Color.parseColor("Red"));
        ansD.setBackgroundColor(Color.parseColor("Red"));

        answer = map.get(question.getText().toString());//Using the hash map to set the correct answer

        if(ansA.getText().toString() == answer){

            ansA.setBackgroundColor(Color.parseColor("Green"));


        }else if(ansB.getText().toString() == answer){

            ansB.setBackgroundColor(Color.parseColor("Green"));


        }else if(ansC.getText().toString() == answer){

            ansC.setBackgroundColor(Color.parseColor("Green"));


        }else if(ansD.getText().toString() == answer){

            ansD.setBackgroundColor(Color.parseColor("Green"));


        }



    }

    void loadNewQuestion(){//This function loads a new question

        ansA.setBackgroundColor(Color.parseColor("#3399FF"));
        ansB.setBackgroundColor(Color.parseColor("#3399FF"));
        ansC.setBackgroundColor(Color.parseColor("#3399FF"));
        ansD.setBackgroundColor(Color.parseColor("#3399FF"));

        if(listDef.size() == 1){

            results.setText("Results");

        }



        if(listDef.size() == 0){//If the current index is equal to the amount of questions then end the quiz

            finishQuiz();

            return;

        }



        //this sets the random selection for the questions
        long seed = System.nanoTime();

        //shuffles both the lists
        Collections.shuffle(listDef, new Random(seed));
        Collections.shuffle(listTerm, new Random(seed));

        //Setting the question
        question.setText(listDef.get(0));

        thisQuestion = listDef.get(0);
        //Deleting the index for the definition list
        listDef.remove(thisQuestion); //- keeps crashing the program

        //creating a list for the buttons
        ArrayList<String> listButtonsAns = new ArrayList<>();

        int counter = 0;

        answer = map.get(question.getText().toString());//Using the hash map to set the correct answer




        //Checking for 3 wrong and one right answer

        while (listButtonsAns.size() < 3){

            if((listTerm.get(counter)!= answer) && (!listButtonsAns.contains(listTerm.get(counter)))){


                listButtonsAns.add(listTerm.get(counter));


            }

            counter++;


        }


        listButtonsAns.add(answer);//Correct Answer

        Collections.shuffle(listButtonsAns);


        //Setting the buttons
        ansA.setText(listButtonsAns.get(0));
        ansB.setText(listButtonsAns.get(1));
        ansC.setText(listButtonsAns.get(2));
        ansD.setText(listButtonsAns.get(3));

        //listButtonsAns.removeAll(listButtonsAns);


    };




    void finishQuiz()//This handles what happnens when the quiz is finished
    {

        String status = "";

        if(score > 10 * 0.60){//If they get more than 60 percent they pass

            status = "passed";

        }else//if not they failed
        {

            status = "failed";
        }

        str = "You have "+ status + "\nYour score is " + Math.round(score) + " out of " + 10;

        // Create the Intent object of this class Context() to Second_activity class
        Intent intent = new Intent(getApplicationContext(), End.class);
        // now by putExtra method put the value in key, value pair key is
        // message_key by this key we will receive the value, and put the string
        intent.putExtra("Key", str);
        // start the Intent
        startActivity(intent);



    }


}