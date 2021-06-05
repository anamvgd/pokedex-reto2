package com.example.pokedex.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pokedex.R;

public class ListActivity extends AppCompatActivity {

    private RecyclerView pokemon_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        pokemon_list =findViewById(R.id.pokemon_list);
        pokemon_list.setHasFixedSize(true);

    }
}