package com.ifsc.listagem_planetas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ifsc.listagem_planetas.PlanetaAdapterRecyclerView;
import com.ifsc.listagem_planetas.PlanetasDao;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        PlanetasDao planetasDao = new PlanetasDao();

        PlanetaAdapterRecyclerView padapterRV = new PlanetaAdapterRecyclerView(
                this,
                R.layout.item_lista,
                planetasDao.getPlanetas()
        );

        recyclerView.setAdapter(padapterRV);
    }
}