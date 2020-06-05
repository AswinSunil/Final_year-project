package com.example.detecttelecomconnectivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void sendMessage(View view)
    {
        Intent intent = new Intent(Main2Activity.this, MapsActivity.class);
        startActivity(intent);
    }
}

