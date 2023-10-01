package com.example.luckyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class LuckyNumberActivity extends AppCompatActivity {

    TextView welcome_txt, lucky_number;
    Button share_number_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_number);

        welcome_txt = findViewById(R.id.textView2);
        lucky_number = findViewById(R.id.lucky_number_txt);
        share_number_btn = findViewById(R.id.share_number_btn);

        // Username
        Intent i = getIntent();
        String userName = i.getStringExtra("name");

        // Random Number Generated
        int random_num = generateRandomNumber();

        lucky_number.setText(""+random_num); //this will show the random generated number into the text view for the lucky number


        share_number_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareData(userName, random_num);
            }
        });


    }

    public int generateRandomNumber(){

        Random random = new Random();
        int upper_limit = 1000;

        int randomNumberGenerated = random.nextInt(upper_limit);//this will generate random number between 0 and 999
        return randomNumberGenerated;

    }

    public void shareData(String username, int randomNum){

        // Implicit Intents
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain"); //setting the type as plain text

        //convert the int to string
        String number = String.valueOf(randomNum);

        // creating and passing the subject and body of the email
        i.putExtra(Intent.EXTRA_SUBJECT, username + " got lucky today!");
        i.putExtra(Intent.EXTRA_TEXT, "His lucky number is: " +number);


        startActivity(Intent.createChooser(i, "Choose a platform"));



    }



}