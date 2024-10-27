package com.example.organizador;

import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class SplachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splach);


        final Intent i = new Intent(SplachActivity.this, MainActivity.class);
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               startActivity(i);
               finish();
           }
       }, 1000);
    }
}