package com.example.pokedex.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.pokedex.R;
import com.example.pokedex.listLogic.PokemonAdapter;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView pokemon_list;
    private LinearLayoutManager layoutManager;
    private PokemonAdapter adapter;

    private EditText editText_atrapar;
    private EditText editText_buscar;
    private Button button_atrapar;
    private ImageButton button_buscar;
    private ImageButton button_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        pokemon_list = findViewById(R.id.pokemon_list);
        editText_atrapar = findViewById(R.id.editText_atrapar);
        editText_buscar = findViewById(R.id.editText_buscar);
        button_atrapar = findViewById(R.id.button_atrapar);
        button_buscar = findViewById(R.id.button_search);
        button_back = findViewById(R.id.button_back2);

        button_atrapar.setOnClickListener(this);
        button_back.setOnClickListener(
                v -> {
                    Intent intent = new Intent(this, UserEntry.class);
                    startActivity(intent);
                });

        pokemon_list.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        pokemon_list.setLayoutManager(layoutManager);

        adapter = new PokemonAdapter(this);
        pokemon_list.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_atrapar:

                //Aqui se hace lo de firebase.. i guess
                System.out.println("atrapadaaaaa");

                break;
        }
    }
}