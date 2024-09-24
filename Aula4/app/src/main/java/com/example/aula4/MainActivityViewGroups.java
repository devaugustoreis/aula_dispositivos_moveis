package com.example.aula4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivityViewGroups extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(getApplicationContext());
        Button b = new Button(this);
        b.setText("shazam!");
        linearLayout.addView(b);

        setContentView(R.layout.activity_main_view_groups);
    }
}