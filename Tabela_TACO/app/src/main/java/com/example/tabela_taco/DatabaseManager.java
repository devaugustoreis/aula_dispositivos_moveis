package com.example.tabela_taco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseManager {

    private static final String DATABASE_NAME = "taco_converted.sqlite";
    private static final String DATABASE_PATH = "/data/data/com.example.tabela_taco/databases/";
    private final Context context;


    public DatabaseManager(Context context) {
        this.context = context;
    }


    public void copyDatabase() {
        File databaseFile = new File(DATABASE_PATH + DATABASE_NAME);

        if (!databaseFile.exists()) {
            try {
                File databaseFolder = new File(DATABASE_PATH);
                if (!databaseFolder.exists()) {
                    databaseFolder.mkdirs();
                }

                InputStream input = context.getAssets().open(DATABASE_NAME);
                OutputStream output = new FileOutputStream(databaseFile);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = input.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }

                output.flush();
                output.close();
                input.close();

                Log.i("DatabaseManager", "Banco de dados copiado com sucesso!");
            } catch (Exception e) {
                Log.e("DatabaseManager", "Erro ao copiar o banco de dados: " + e.getMessage());
            }
        } else {
            Log.i("DatabaseManager", "Banco de dados já existe, não é necessário copiar.");
        }
    }


    public SQLiteDatabase openDatabase() {
        return SQLiteDatabase.openDatabase(DATABASE_PATH + DATABASE_NAME, null, SQLiteDatabase.OPEN_READWRITE);
    }
}
