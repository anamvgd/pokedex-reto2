package com.example.pokedex.listLogic;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.R;
import com.example.pokedex.model.Pokemon;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonView> {

    private ArrayList<Pokemon> pokemons;
    private Activity activity;

    public PokemonAdapter(Activity activity){
        pokemons = new ArrayList<>();
        this.activity = activity;

        Pokemon pikachu = new Pokemon();
        pikachu.setName("Pikachu");

        Pokemon charmander = new Pokemon();
        charmander.setName("charmander");

        pokemons.add(pikachu);
        pokemons.add(charmander);
    }

    public void addPokemon(Pokemon pokemon){
        pokemons.add(pokemon);
        this.notifyDataSetChanged();
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
        holder.getName().setText(pokemons.get(position).getName());
        //holder.getImage().setImageBitmap(pokemons.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }



}
