package com.example.pokedex.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pokedex.R;
import com.example.pokedex.listLogic.PokemonAdapter;

public class ListActivity extends AppCompatActivity {

    private RecyclerView pokemon_list;
    private LinearLayoutManager layoutManager;
    private PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        pokemon_list =findViewById(R.id.pokemon_list);
        pokemon_list.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        pokemon_list.setLayoutManager(layoutManager);

        adapter = new PokemonAdapter();
        pokemon_list.setAdapter(adapter);
    }
}