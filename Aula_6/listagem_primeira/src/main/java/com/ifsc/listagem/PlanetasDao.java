package com.ifsc.listagem;


import java.util.ArrayList;

public class PlanetasDao {
    ArrayList<Planeta> planetas;

    public PlanetasDao() {
        planetas = new ArrayList<>();
        planetas.add(new Planeta("mercurio", R.drawable.mercury));
        planetas.add(new Planeta("venus", R.drawable.venus));
    }

    public ArrayList<Planeta> getPlanetas(){
        return this.planetas;
    }

}
