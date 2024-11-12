package com.example.listagem_launcher;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
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

public class AppAdapter extends ArrayAdapter<ResolveInfo> {

    int myResource;

    public AppAdapter(@NonNull MainActivity mainActivity, int item_lista, @NonNull List<ResolveInfo> packageInfoList) {
        super(mainActivity, item_lista, packageInfoList);
        myResource = item_lista;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);

        // Inflando layout do item da lista
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(myResource, parent, false);

        //Recuperando o objeto ApplicationInfo da posição atual
        ResolveInfo resolveInfo = getItem(position);

        //Recuperando os componentes do layout
        TextView textViewAppName = view.findViewById(R.id.app_name);
        ImageView imageViewAppIcon = view.findViewById(R.id.app_icon);

        //Setando os valores nos componentes
        imageViewAppIcon.setImageDrawable(resolveInfo.loadIcon(getContext().getPackageManager()));
        textViewAppName.setText(resolveInfo.loadLabel(getContext().getPackageManager()));
        return view;
    }


}
