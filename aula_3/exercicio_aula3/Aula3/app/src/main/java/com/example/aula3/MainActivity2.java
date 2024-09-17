package com.example.aula3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity2 extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn = findViewById(R.id.btnFechar);

        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fecharAtividade();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView tv = findViewById(R.id.tv2);
        Bundle bundle = getIntent().getExtras();
        String string = bundle.getString("msg");

        tv.setText(getIntent().getExtras().getString("msg"));
    }

    public void fecharAtividade() {
        finish();
    }
}