package com.ifsc.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase bd;
    EditText editText;
    Button btn;
    ListView listView;
    ArrayList<String> listaNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bd = openOrCreateDatabase("notinhas", MODE_PRIVATE, null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS notas ( id INTEGER PRIMARY KEY AUTOINCREMENT, txt VARCHAR )");

        listaNotas = obterNotas();

        editText = findViewById(R.id.editTextText);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("txt", editText.getText().toString());

                bd.insert("notas", null, cv);
                listaNotas = obterNotas();
            }
        });

        listView = findViewById(R.id.listView);

        ArrayAdapter<String> array = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                listaNotas
        );

        listView.setAdapter(array);
    }

    public ArrayList<String> obterNotas() {
        ArrayList<String> notas = new ArrayList<String>();

        Cursor cursor = bd.rawQuery("SELECT * FROM notas", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            notas.add(cursor.getString(cursor.getColumnIndex("txt")));

            cursor.moveToNext();
        }

        return notas;
    }

}