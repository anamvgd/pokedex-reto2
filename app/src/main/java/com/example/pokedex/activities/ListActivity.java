package com.example.pokedex.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pokedex.R;
import com.example.pokedex.connection.Constants;
import com.example.pokedex.connection.HTTPSWebUtilDomi;
import com.example.pokedex.listLogic.PokemonAdapter;
import com.example.pokedex.model.Pokemon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView pokemon_list;
    private LinearLayoutManager layoutManager;
    private PokemonAdapter adapter;

    private EditText editText_atrapar;
    private EditText editText_buscar;
    private Button button_atrapar;
    private ImageButton button_buscar;
    private ImageButton button_back;

    private String trainer;

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
        trainer = getIntent().getExtras().getString("trainer");

        button_atrapar.setOnClickListener(this);
        button_back.setOnClickListener(
                v -> {
                    Intent intent = new Intent(this, UserEntry.class);
                    startActivity(intent);
                });

        pokemon_list.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        pokemon_list.setLayoutManager(layoutManager);

        adapter = new PokemonAdapter(this, trainer);
        pokemon_list.setAdapter(adapter);

        getOwnPokemos();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_atrapar:
                atraparPokemon();
                editText_atrapar.setText("");
                System.out.println("atrapadaaaaa");
                break;
        }
    }

    private void atraparPokemon(){
        HTTPSWebUtilDomi https = new HTTPSWebUtilDomi();
        Gson gson = new Gson();

        new Thread(
                () -> {
                    String response = https.GETrequest(Constants.APIURL + editText_atrapar.getText().toString());

                    try {
                        JSONObject pokemonJson = new JSONObject(response);

                        String name = pokemonJson.getString("name");
                        String image = pokemonJson.getJSONObject("sprites").getString("front_default");
                        StringBuilder type = new StringBuilder();

                        JSONArray types = pokemonJson.getJSONArray("types");

                        for(int i = 0; i < types.length(); i++){
                            type.append(types.getJSONObject(i).getJSONObject("type").getString("name")).append(" ");
                        }

                        JSONArray stats = pokemonJson.getJSONArray("stats");

                        String life = stats.getJSONObject(0).getString("base_stat");
                        String attack = stats.getJSONObject(1).getString("base_stat");
                        String defense = stats.getJSONObject(2).getString("base_stat");
                        String speed = stats.getJSONObject(3).getString("base_stat");

                        Pokemon pokemon = new Pokemon(name, image, type.toString(), defense, attack, speed, life);

                        String uploadPokemon = gson.toJson(pokemon);

                        new Thread(
                                ()->{
                                    https.PUTrequest(Constants.BASEURL+ "trainers/" + trainer + "/" + pokemon.getName() + ".json", uploadPokemon );
                                }
                        ).start();

                        adapter.addPokemon(pokemon);

                        runOnUiThread(() -> adapter.notifyDataSetChanged());

                    } catch (JSONException e) {
                        runOnUiThread(() -> {Toast.makeText(this, "Ups, no existe tal pokemon", Toast.LENGTH_SHORT).show();});
                    }

                }
        ).start();

    }

    private void getOwnPokemos(){
        HTTPSWebUtilDomi https = new HTTPSWebUtilDomi();


        new Thread(
                () -> {
                    String response = https.GETrequest(Constants.BASEURL + "trainers/" + trainer + ".json");
                    Type tipo = new TypeToken<HashMap<String, Pokemon>>() {}.getType();
                    try{

                        Gson gson = new Gson();
                        HashMap<String, Pokemon> pokemons = gson.fromJson(response, tipo);
                        //System.out.println(pokemons.size());

                        pokemons.forEach(
                                (key, value) -> {
                                    adapter.addPokemon(value);
                                }
                        );

                        runOnUiThread(
                                () ->{
                                    adapter.notifyDataSetChanged();
                                });

                    }catch (Exception e){
                        runOnUiThread(() -> {
                            Toast.makeText(this, "Empieza a atrapar pokemons!", Toast.LENGTH_SHORT).show();
                        });
                    }

                }
        ).start();
    }
}