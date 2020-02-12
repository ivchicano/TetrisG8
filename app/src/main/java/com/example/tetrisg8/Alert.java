package com.example.tetrisg8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;


import androidx.appcompat.app.AppCompatActivity;

public class Alert extends AppCompatActivity {
    static Activity ventanaNombre;
    DatabaseClass myDb;
    EditText editName;
    String name;
    private Switch sw;
    private String activado;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_dialog);
        myDb = new DatabaseClass(this);
        //Mostramos la puntuación
        editName = (EditText)findViewById(R.id.nombreUsuario);
        ventanaNombre=this;


    }
    public void buttonAccept(View v){
        name = editName.getText().toString();
        GoToMain();
    }

     public void GoToMain() {
        /*Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();*/
       Intent intent = new Intent(this, MainActivity.class);
       intent.putExtra("name", this.name);
       startActivity(intent);

       }
       }






