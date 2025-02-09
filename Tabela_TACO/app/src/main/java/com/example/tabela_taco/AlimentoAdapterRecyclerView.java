package com.example.tabela_taco;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AlimentoAdapterRecyclerView extends RecyclerView.Adapter<AlimentoAdapterRecyclerView.AlimentoVH>{

    Context myContext;
    int myResource;
    List<Alimento> myListAlimento;

    public AlimentoAdapterRecyclerView(Context context, int resource, List<Alimento> objs) {
        myContext = context;
        myResource = resource;
        myListAlimento = objs;
    }

    @NonNull
    @Override
    public AlimentoVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = View.inflate(myContext, myResource, null);
        return new AlimentoVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AlimentoVH alimentoVH, int i) {
        Alimento a = (Alimento) myListAlimento.get(i);
        alimentoVH.tvNome.setText(a.nome);
        alimentoVH.tvPreparo.setText(a.preparo);
        alimentoVH.tvCategoria.setText(a.categoria);
        alimentoVH.tvDado1.setText(a.dado1);
    }

    @Override
    public int getItemCount() {
        return myListAlimento.size();
    }

    public class AlimentoVH extends RecyclerView.ViewHolder {
        TextView tvNome;
        TextView tvPreparo;
        TextView tvCategoria;
        TextView tvDado1;

        public AlimentoVH(@NonNull View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.textViewAlimento);
            tvPreparo = itemView.findViewById(R.id.textViewPreparo);
            tvCategoria = itemView.findViewById(R.id.textViewCategoria);
            tvDado1 = itemView.findViewById(R.id.textViewDado1);
        }
    }

}
