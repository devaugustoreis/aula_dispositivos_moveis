package br.ifsc.edu.br.contador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myBtn = findViewById(R.id.button);
        TextView myText = findViewById(R.id.textView);

        EditText editTextInicial = findViewById(R.id.editTextInicial);
        EditText editTextFinal = findViewById(R.id.editTextFinal);

        myBtn.setText("Sortear!");

        myBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();

                int nInicial = Integer.parseInt(editTextInicial.getText().toString());
                int nFinal = Integer.parseInt(editTextFinal.getText().toString());

                myText.setText(Integer.toString(random.nextInt(nFinal + 1 - nInicial) + nInicial));
            }
        });
    }
}