package com.example.tetrisg8;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


//Ventana principal en la que se jugará la partida

public class MainActivity extends AppCompatActivity {
    static Activity ventanaTablero;
    private FuncionamientoJuego start;
    private Tablero tab = new Tablero();
    private GameView gameView;
    private FichaView fichaView;
    private TextView caja_score;
    private String namePlayer;
    private Button botonPiezaRandom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ventanaTablero = this;
        this.caja_score = (TextView) findViewById(R.id.scoreBoxContent);
        this.botonPiezaRandom = (Button) findViewById(R.id.buttonPiezaRandom);
        //to set score
        //this.caja_score.setText(String.valueOf());
        tab.inicializarTablero();
        this.gameView = new GameView(this, tab);
        this.fichaView = new FichaView(this);

        LinearLayout gameLayout = (LinearLayout) findViewById(R.id.gameView);
        LinearLayout fichaLayout = (LinearLayout) findViewById(R.id.fichaView);

        gameLayout.addView(this.gameView);
        fichaLayout.addView(this.fichaView);
        Intent intent = getIntent();
        this.namePlayer = intent.getStringExtra("name");
        start = new FuncionamientoJuego(gameView, fichaView, tab,namePlayer, this);

        start.partida();
    }




    //move left
    public void moveLeft(View view) {
        //Se intenta desplazar la ficha a la izquierda, si ha sido posible se actualiza el canvas
        if (tab.izquierda())
            gameView.invalidate();
    }

    //move right
    public void moveRight(View view) {
        //Se intenta desplazar la ficha a la derecha, si ha sido posible se actualiza el canvas
        if (tab.derecha())
            gameView.invalidate();
    }

    //rotate
    public void rotateAction(View view) {
        //Se intenta girar la ficha a la derecha, si ha sido posible se actualiza el canvas
        if (tab.rotar(-90))
            gameView.invalidate();
    }
    public void rotarInverso(View view) {
        //Se intenta girar la ficha a la derecha, si ha sido posible se actualiza el canvas
        if (tab.rotar(90))
            gameView.invalidate();
    }

    public void pantallaGameOver() {
        Intent intent = new Intent(this,TakePhoto.class);
        intent.putExtra("puntuacion", start.getPuntuacion());
        intent.putExtra("tiempo", start.getTiempoTranscurrido());
        //Mandamos la puntuacion a la ventana de game over
        startActivity(intent);
    }

    //Para el timer cuando se abre otro activity por encima
    @Override
    protected void onPause() {
        super.onPause();
        start.finalizarTimer();
        start.stopMediaPlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        start.finalizarTimer();
        start.stopMediaPlayer();
    }

    public void actualizarPuntuacion(int puntuacion) {
        caja_score.setText(String.valueOf(puntuacion));
    }

    public void DownAction(View view) {
        while (tab.posibleBajar("normal")) {
            tab.bajarPieza("normal", 1);
        }
    }

    public void activarBotonPiezaRandom(){
        botonPiezaRandom.setVisibility(View.VISIBLE);
    }

    public void desactivarBotonPiezaRandom(){
        botonPiezaRandom.setVisibility(View.INVISIBLE);
    }

    public boolean botonRandomActivado(){
        return botonPiezaRandom.getVisibility() == View.VISIBLE;
    }


    public void piezaRandom(View view){
        Pieza piezaRand = start.generarPieza(0);
        tab.setEnjuego(piezaRand);
        desactivarBotonPiezaRandom();
        start.setTiempoBotonDesactivado(0);
        start.reducirPuntuacionPiezaRandom();
    }
}


