package com.example.pokedex.listLogic;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.R;
import com.example.pokedex.activities.ListActivity;
import com.example.pokedex.activities.PokemonActivity;
import com.example.pokedex.model.Pokemon;

public class PokemonView extends RecyclerView.ViewHolder {
    
    private ConstraintLayout root;
    private TextView name;
    private ImageView image;
    private String trainer;
    private Pokemon pokemon;

    private Activity activity;

    public PokemonView(ConstraintLayout root) {
        super(root);
        this.root = root;
        name = root.findViewById(R.id.pokerowName);
        image = root.findViewById(R.id.pokerowImage);


        root.setOnClickListener(
                v -> {

                    Intent intent = new Intent(activity ,PokemonActivity.class);
                    intent.putExtra("trainer", trainer);

                    intent.putExtra("name",pokemon.getName());
                    intent.putExtra("img",pokemon.getImage());
                    intent.putExtra("type",pokemon.getType());
                    intent.putExtra("attack",pokemon.getAttack());
                    intent.putExtra("defense",pokemon.getDefense());
                    intent.putExtra("speed",pokemon.getSpeed());
                    intent.putExtra("hp",pokemon.getLife());
                    intent.putExtra("url", trainer);

                    activity.startActivityForResult(intent, 20);

                });

    }

    public ConstraintLayout getRoot() {
        return root;
    }

    public TextView getName() {
        return name;
    }

    public ImageView getImage() {
        return image;
    }

    public void setActivity(Activity activity){
        this.activity = activity;
    }

    public void setTrainer(String trainer){
        this.trainer = trainer;
    }

    public void setPokemon(Pokemon pokemon) { this.pokemon = pokemon; }
}
