package com.example.listagem_launcher;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AppAdapter extends ArrayAdapter<ApplicationInfo> {
    public AppAdapter(@NonNull MainActivity mainActivity, int item_lista, @NonNull List<ApplicationInfo> packageInfoList) {
        super(mainActivity, item_lista, packageInfoList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);

        // Inflando layout do item da lista
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.item_lista, parent, false);

        //Recuperando o objeto ApplicationInfo da posição atual
        ApplicationInfo applicationInfo = getItem(position);

        //Recuperando os componentes do layout
        TextView textViewAppName = view.findViewById(R.id.app_name);
        ImageView imageViewAppIcon = view.findViewById(R.id.app_icon);

        //Setando os valores nos componentes
        imageViewAppIcon.setImageDrawable(applicationInfo.loadIcon(getContext().getPackageManager()));
        textViewAppName.setText(applicationInfo.loadLabel(getContext().getPackageManager()));
        return view;
    }


}
