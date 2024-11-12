package com.ifsc.listagem_planetas;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlanetaAdapterRecyclerView extends RecyclerView.Adapter<PlanetaAdapterRecyclerView.PlanetaVH> {

    Context myContext;
    int myResource;
    List<Planeta> myListPlaneta;

    public PlanetaAdapterRecyclerView(Context context, int resource, List<Planeta> objs) {
        myContext = context;
        myResource = resource;
        myListPlaneta = objs;
    }

    @NonNull
    @Override
    public PlanetaVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = View.inflate(myContext, myResource, null);
        return new PlanetaVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetaVH planetaVH, int i) {
        Planeta p = (Planeta) myListPlaneta.get(i);
        planetaVH.imageView.setImageResource(p.imgPlaneta);
        planetaVH.tvNome.setText(p.nome);
    }

    @Override
    public int getItemCount() {
        return myListPlaneta.size();
    }

    public class PlanetaVH extends RecyclerView.ViewHolder {
        TextView tvNome;
        ImageView imageView;

        public PlanetaVH(@NonNull View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
