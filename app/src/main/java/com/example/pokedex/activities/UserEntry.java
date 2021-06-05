package com.example.pokedex.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pokedex.R;

public class UserEntry extends AppCompatActivity implements View.OnClickListener{

    private Button button_ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_entry);

        button_ingresar = findViewById(R.id.button_ingresar);
        button_ingresar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_ingresar:

                Intent intent = new Intent(this, ListActivity.class);
                startActivity(intent);

                break;
        }
    }
}