package com.example.pokedex.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pokedex.R;
import com.example.pokedex.connection.Constants;
import com.example.pokedex.connection.HTTPSWebUtilDomi;
import com.example.pokedex.model.Pokemon;
import com.google.gson.Gson;

import java.util.HashMap;

public class UserEntry extends AppCompatActivity implements View.OnClickListener{

    private Button button_ingresar;
    private String trainer;
    private EditText editText_username;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_entry);

        button_ingresar = findViewById(R.id.button_ingresar);
        button_ingresar.setOnClickListener(this);

        editText_username = findViewById(R.id.editText_username);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.INTERNET
                //Manifest.permission.WRITE_EXTERNAL_STORAGE,
                //Manifest.permission.READ_EXTERNAL_STORAGE,
                //Manifest.permission.CALL_PHONE
        }, 11);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_ingresar:

                trainer = editText_username.getText().toString();
                System.out.println(trainer);

                if(trainer.length()>0) {
                    Gson gson = new Gson();
                    String json = gson.toJson(trainer);
                    HTTPSWebUtilDomi https = new HTTPSWebUtilDomi();
                    new Thread(
                            () -> {
                                if(https.GETrequest(Constants.BASEURL + "trainers/" + trainer + ".json").equals("null"))
                                    //System.out.println("hola");
                                    https.PUTrequest(Constants.BASEURL + "trainers/" + trainer + ".json", json);
                            }
                    ).start();

                    Intent intent = new Intent(this, ListActivity.class);
                    intent.putExtra("trainer", trainer);
                    startActivity(intent);

                    break;
                }
        }
    }


    public String getTrainer() {
        return trainer;
    }
}