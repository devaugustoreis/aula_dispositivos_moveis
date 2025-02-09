package com.example.aula3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btnA, btnB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnA = findViewById(R.id.btnClickA);
        btnB = findViewById(R.id.btnClickB);
        EditText et = findViewById(R.id.editText);

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                String mensagem = et.getText().toString();
                intent.putExtra("msg", mensagem);
                startActivity(intent);
            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                startActivity(intent);
            }
        });

        Log.d("Ciclo de Vida", "passou pelo onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Ciclo de vida", "passou pelo onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Ciclo de vida", "passou pelo onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Ciclo de vida", "passou pelo onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Ciclo de vida", "passou pelo onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Ciclo de vida", "passou pelo onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Ciclo de vida", "passou pelo onDestroy");
    }
}