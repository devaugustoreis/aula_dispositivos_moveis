package com.ifsc.listagem;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> nomes;
    ListView listView;
    Button listAddBtn;
    EditText listET;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        listView = findViewById(R.id.listView);
//        nomes = new ArrayList();
//        nomes.add("Kaua");
//        nomes.add("Lucas");
//        nomes.add("Gustavo");
//        nomes.add("Augusto");
//        nomes.add("Eduarda");
//        nomes.add("Gelasio");

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1,
//                android.R.id.text1,
//                nomes
//        );
//
//        listView.setAdapter(adapter);
//
//        listAddBtn = findViewById(R.id.button);
//        listET = findViewById(R.id.editTextText);
        /*
        listAddBtn.setOnClickListener(v -> {
            String nome = listET.getText().toString();
            nomes.add(nome);
            carregarLista();
        });
        */

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        PlanetasDao planetasDao = new PlanetasDao();

        PlanetaAdapterRecyclerView padapterRV = new PlanetaAdapterRecyclerView(this,
                R.layout.item_lista,
                planetasDao.getPlanetas()
        );

        recyclerView.setAdapter(padapterRV);

//        carregarLista();
    }

    public void carregarLista() {
        /* Lista de String
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                nomes
        );

        listView.setAdapter(adapter);
        */

//        PlanetasDao planetasDao = new PlanetasDao();
//        PlanetasAdapter adapter = new PlanetasAdapter(this, R.layout.item_lista, planetasDao.getPlanetas());

//        listView.setAdapter(adapter);
    }

}