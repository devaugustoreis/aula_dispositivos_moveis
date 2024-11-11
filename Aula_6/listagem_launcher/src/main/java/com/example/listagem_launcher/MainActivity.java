package com.example.listagem_launcher;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listView = findViewById(R.id.listview_apps);

        PackageManager packageManager = getPackageManager();
        if (packageManager == null) {
            Toast.makeText(this, "Erro: PackageManager não disponível", Toast.LENGTH_SHORT).show();
            return;
        }

        List<ApplicationInfo> packageInfoList = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        if (packageInfoList == null || packageInfoList.isEmpty()) {
            Toast.makeText(this, "Nenhum aplicativo encontrado", Toast.LENGTH_SHORT).show();
            return;
        }


        /* Cria um Intent com a ação ACTION_MAIN, que é usada para filtrar aplicativos
        que tenham uma atividade principal. O segundo argumento null indica que não há
        um componente específico a ser direcionado neste momento.*/
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> appList = packageManager.queryIntentActivities(mainIntent, 0);

        AppAdapter appAdapter = new AppAdapter(this,R.layout.item_lista, packageInfoList);
        listView.setAdapter(appAdapter);

        // Adicionando o listener de clique para cada item da lista
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ApplicationInfo applicationInfo = (ApplicationInfo) parent.getItemAtPosition(position);
                String packageName = applicationInfo.packageName;

                Toast.makeText(MainActivity.this, "Abrindo: " + applicationInfo.loadLabel(packageManager), Toast.LENGTH_SHORT).show();

                Intent intent = packageManager.getLaunchIntentForPackage(packageName);
                if (intent != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Não foi possível abrir o aplicativo", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}