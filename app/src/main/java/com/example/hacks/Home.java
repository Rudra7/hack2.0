package com.example.hacks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    public TextView text;
    public Button button;
    public void proceed(View view){
        Intent intent=new Intent(getApplicationContext(),Dump.class);
        startActivity(intent);
    }
    public void func(View view)
    {
        text.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        text=findViewById(R.id.text);
        button=findViewById(R.id.button);
    }
}
