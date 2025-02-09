package com.example.tabela_taco;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AlimentoAdapter extends ArrayAdapter<Alimento> {
    int myResource;

    public AlimentoAdapter(@NonNull Context context, int resource, @NonNull List<Alimento> objects) {
        super(context, resource, objects);
        myResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View v = layoutInflater.inflate(myResource, parent, false);

        TextView tvAlimento = v.findViewById(R.id.textViewAlimento);
        TextView tvPreparo = v.findViewById(R.id.textViewPreparo);
        TextView tvCategoria = v.findViewById(R.id.textViewCategoria);
        TextView tvDado1 = v.findViewById(R.id.textViewDado1);

        Alimento alimento = getItem(position);

        tvAlimento.setText(alimento.nome);
        tvPreparo.setText(alimento.preparo);
        tvCategoria.setText(alimento.categoria);
        tvDado1.setText(alimento.dado1);

        return v;
    }
}
