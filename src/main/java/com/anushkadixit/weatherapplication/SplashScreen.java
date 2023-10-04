package com.anushkadixit.weatherapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {

    TextView anushka,weatherSplash;
    LottieAnimationView lottie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        anushka=findViewById(R.id.anushka);
        weatherSplash=findViewById(R.id.weathersplash);
        lottie=findViewById(R.id.lottie);





        new Handler().postDelayed(new Runnable() {         //Handler class handles the asynchronous processing,i.e parallel threading.
            @Override
            public void run() {

                Intent iHome;  // creating intent for splash screen
                iHome=new Intent(SplashScreen.this,MainActivity.class);
                startActivity(iHome);

            }
        },3000);



    }
}