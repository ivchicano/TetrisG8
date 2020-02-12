package com.example.tetrisg8;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

//Ventana inicial de arranque

public class StartGame extends AppCompatActivity {
    static Activity ventanaInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        //Para poder cerrar el activity desde otra ventana
        ventanaInicio = this;
    }

    public void jugar(View view) {
        Intent goToMain = new Intent(this,Alert.class);
        startActivity(goToMain);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.disenomenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.config) {
            Intent i=new Intent(this,Configuracion.class);
            startActivity(i);
        }
        return true;
    }

}

