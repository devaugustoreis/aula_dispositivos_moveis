package com.example.tabela_taco;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseManager databaseManager;
    SQLiteDatabase banco;
    RecyclerView recyclerView;
    AlimentoAdapterRecyclerView alimentoAdapterRV;
    String tabela = "alimentos_lip_acucares_100g";
    String colunaDado1 = "acucares_totais_g";
    TextView cabecalhoDado1;
    EditText editTextBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseManager = new DatabaseManager(this);
        databaseManager.copyDatabase();
        banco = databaseManager.openDatabase();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cabecalhoDado1 = findViewById(R.id.cabecalhoDado1);
        cabecalhoDado1.setText("Açúcares Totais (g)");
        atualizarRecyclerView(tabela, colunaDado1, "");

        editTextBuscar = findViewById(R.id.inputBuscar);
        editTextBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                atualizarRecyclerView(tabela, colunaDado1, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selectedButton = findViewById(checkedId);
            String opcao = selectedButton.getText().toString();

            switch (opcao) {
                case "Lípidios":
                    tabela = "alimentos_lip_acucares_100g";
                    colunaDado1 = "acucares_totais_g";
                    cabecalhoDado1.setText("Açúcares Totais (g)");
                    break;
                case "Macros":
                    tabela = "alimentos_macros_100g";
                    colunaDado1 = "proteinasg";
                    cabecalhoDado1.setText("Proteínas (g)");
                    break;
                case "Minerais":
                    tabela = "minerais_100g";
                    colunaDado1 = "sodio_mg";
                    cabecalhoDado1.setText("Sódio (mg)");
                    break;
                case "Vitaminas":
                    tabela = "vitaminas";
                    colunaDado1 = "vitamina_c_mg";
                    cabecalhoDado1.setText("Vitamina C (mg)");
                    break;
            }

            atualizarRecyclerView(tabela, colunaDado1, editTextBuscar.getText().toString());
        });
    }


    private void atualizarRecyclerView(String tabela, String colunaDado1, String busca) {
        ArrayList<Alimento> listaAlimentos = consultarBanco(tabela, colunaDado1, busca);

        alimentoAdapterRV = new AlimentoAdapterRecyclerView(this, R.layout.item_lista, listaAlimentos);
        recyclerView.setAdapter(alimentoAdapterRV);
    }


    private ArrayList<Alimento> consultarBanco(String tabela, String colunaDado1, String busca) {
        ArrayList<Alimento> arrayAlimentos = new ArrayList<>();

        banco = databaseManager.openDatabase();
        String query = "SELECT nome_alimento, forma_preparo, categoria, " + colunaDado1 + " FROM " + tabela;
        Cursor cursor;

        if (!busca.isEmpty()) {
            query += " WHERE nome_alimento LIKE ?";
            String[] selectionArgs = new String[]{"%" + busca + "%"};
            cursor = banco.rawQuery(query, selectionArgs);

        } else {
            cursor = banco.rawQuery(query, null);
        }

        if (cursor.moveToFirst()) {
            do {
                String nome = cursor.getString(cursor.getColumnIndex("nome_alimento"));
                String preparo = cursor.getString(cursor.getColumnIndex("forma_preparo"));
                String categoria = cursor.getString(cursor.getColumnIndex("categoria"));
                String dado1 = cursor.getString(cursor.getColumnIndex(colunaDado1));

                if (dado1 == null) {
                    dado1 = "0";
                }

                Alimento novoAlimento = new Alimento(nome, preparo, categoria, dado1);
                arrayAlimentos.add(novoAlimento);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return arrayAlimentos;
    }
}
