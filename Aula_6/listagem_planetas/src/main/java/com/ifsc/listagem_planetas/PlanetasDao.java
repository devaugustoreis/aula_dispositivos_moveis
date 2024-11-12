package com.ifsc.listagem_planetas;


import java.util.ArrayList;

public class PlanetasDao {
    ArrayList<Planeta> planetas;

    public PlanetasDao() {
        planetas = new ArrayList<>();
        planetas.add(new Planeta("Mercúrio", R.drawable.mercury));
        planetas.add(new Planeta("Vênus", R.drawable.venus));
        planetas.add(new Planeta("Terra", R.drawable.earth));
        planetas.add(new Planeta("Marte", R.drawable.mars));
        planetas.add(new Planeta("Jupiter", R.drawable.jupter));
        planetas.add(new Planeta("Saturno", R.drawable.saturn));
        planetas.add(new Planeta("Urano", R.drawable.uranus));
        planetas.add(new Planeta("Netuno", R.drawable.neptune));
    }

    public ArrayList<Planeta> getPlanetas(){
        return this.planetas;
    }

}
