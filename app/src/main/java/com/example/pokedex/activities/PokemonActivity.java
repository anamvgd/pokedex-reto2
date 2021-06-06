package com.example.pokedex.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pokedex.R;

public class PokemonActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView pokeImage;
    private TextView pokeName;
    private TextView pokeType;
    private TextView pokeDefense;
    private TextView pokeAttack;
    private TextView pokeSpeed;
    private TextView pokeLife;
    private ImageButton button_back;
    private Button button_liberar;

    private String trainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        pokeImage = findViewById(R.id.pokeImage);
        pokeName = findViewById(R.id.pokeName);
        pokeType = findViewById(R.id.pokeType);
        pokeDefense = findViewById(R.id.pokeDefense);
        pokeAttack = findViewById(R.id.pokeAttack);
        pokeSpeed = findViewById(R.id.pokeSpeed);
        pokeLife = findViewById(R.id.pokeLife);
        button_back = findViewById(R.id.button_back);
        button_liberar = findViewById(R.id.button_liberar);

        pokeName.setText(getIntent().getExtras().getString("name"));
        pokeType.setText(getIntent().getExtras().getString("type"));
        pokeDefense.setText(getIntent().getExtras().getString("defense"));
        pokeAttack.setText(getIntent().getExtras().getString("attack"));
        pokeSpeed.setText(getIntent().getExtras().getString("speed"));
        pokeLife.setText(getIntent().getExtras().getString("hp"));

        trainer = getIntent().getExtras().getString("trainer");

        String url = getIntent().getExtras().getString("img");
        Glide.with(this).load(url).fitCenter().into(pokeImage);

        button_back.setOnClickListener(
                v -> {
                    Intent intent = new Intent(this, ListActivity.class);
                    intent.putExtra("trainer", trainer);
                    startActivity(intent);
                });

        button_liberar.setOnClickListener(
                v -> {
                    System.out.println("liberado");
                }
        );

    }

    @Override
    public void onClick(View v) {

    }
}