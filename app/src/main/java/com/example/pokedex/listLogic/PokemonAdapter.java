package com.example.pokedex.listLogic;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokedex.R;
import com.example.pokedex.model.Pokemon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonView> {

    private ArrayList<Pokemon> pokemons;
    private ArrayList<Pokemon> originalP;
    private Activity activity;
    private String trainer;

    public PokemonAdapter(Activity activity, String trainer){
        pokemons = new ArrayList<>();
        originalP = new ArrayList<>();
        originalP.addAll(pokemons);
        this.activity = activity;
        this.trainer = trainer;
    }

    public void filterSearch(String search){
        if(search.length()==0){
            pokemons.clear();
            pokemons.addAll(originalP);
        } else {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                pokemons.clear();
                List<Pokemon> collect = originalP.stream()
                        .filter(i -> i.getName().toLowerCase().contains(search))
                        .collect(Collectors.toList());

                pokemons.addAll(collect);
            }
        }
    }

    public void addPokemon(Pokemon pokemon){
        pokemons.add(pokemon);
        pokemons.sort(new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                if (p1.getCatchDate().after(p2.getCatchDate())) {
                    return 1;
                } else if (p2.getCatchDate().after(p1.getCatchDate())) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        originalP.add(pokemon);
        originalP.sort(new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                if (p1.getCatchDate().after(p2.getCatchDate())) {
                    return 1;
                } else if (p2.getCatchDate().after(p1.getCatchDate())) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
    }

    @NonNull
    @Override
    public PokemonView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //XML --> View
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.pokemonrow, null);
        ConstraintLayout rowroot = (ConstraintLayout) row;
        PokemonView pokemonView = new PokemonView(rowroot);

        return pokemonView;
    }

    @Override
    public void onBindViewHolder(PokemonView holder, int position) {
        holder.setActivity(activity);
        holder.setTrainer(trainer);
        holder.getName().setText(pokemons.get(position).getName());
        //holder.getImage().setImageBitmap(pokemons.get(position).getImage());
        Glide.with(activity).load(pokemons.get(position).getImage()).fitCenter().into(holder.getImage());

    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }
    public ArrayList<Pokemon> getOriginalPokemons() { return originalP; }

    public void setPokemons(ArrayList<Pokemon> originalP, ArrayList<Pokemon> pokemons){
        this.originalP = originalP;
        this.pokemons = pokemons;
    }

}
