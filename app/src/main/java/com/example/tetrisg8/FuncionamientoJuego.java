package com.example.tetrisg8;



import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;

import java.util.Timer;
import java.util.TimerTask;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

//Clase que controla el funcionamiento del juego y de la partida

public class FuncionamientoJuego {
    private int puntuacion;
    private int lineasEliminadas;
    private Tablero tablero;
    private Pieza pieza, piezaSiguiente, piezaExtra;
    private GameView gameView;
    private FichaView fichaView;
    private MainActivity mainActivity;
    private Timer timer;
    private int periodo = 1000;
    private int tiempoTranscurrido;
    private String namePlayer;
    private DatabaseClass db;
    private boolean pendienteAcortar = false;
    private int gamaColores;
    private SharedPreferences pref;
    private DateFormat df;
    private MediaPlayer musica;
    private int cancion = 1;
    private int tiempoBotonDesactivado = -1;


    public FuncionamientoJuego(GameView gameView, FichaView fichaView, Tablero tab, String namePlayer,Context context) {
        this.gameView = gameView;
        this.fichaView = fichaView;
        tablero = tab;
        mainActivity = (MainActivity) context;
        this.namePlayer = namePlayer;
        db = new DatabaseClass(mainActivity);
    }

    public void partida() {
        inicializarPartida();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mainActivity.runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {

                        controlarMusica();
                        controlarPiezaNormal();
                        controlarPiezaExtra();
                        acortarTablero();


                        lineasEliminadas = tablero.lineasCompletas();
                        cambiarColorPiezas(lineasEliminadas);
                        puntuacion += (30*lineasEliminadas);
                        controlarBotonRandom();
                        mainActivity.actualizarPuntuacion(puntuacion);


                        fichaView.invalidate();
                        gameView.invalidate();
                        tiempoTranscurrido += periodo;

                    }
                });
            }
        }, 0, periodo);
    }

    public void inicializarPartida(){
        puntuacion = 0;
        tiempoTranscurrido = 0;
        pref = PreferenceManager.getDefaultSharedPreferences(mainActivity);
        gamaColores = Integer.parseInt(pref.getString("lista", "1"));
        gameView.rellenarArray(gamaColores);
        int[] colores = gameView.getArrayColoresAleatorios();
        fichaView.rellenarArray(gamaColores, colores);
    }

    public void controlarPiezaNormal(){
        df = new SimpleDateFormat("mm:ss");
        if (tablero.getEnjuego() == null) {  //Si es la primera pieza
            pieza = generarPieza(0); // Se genera una pieza aleatoria
            tablero.setEnjuego(pieza);
            piezaSiguiente = generarPiezaSiguiente();
            tablero.setPiezaSiguiente(piezaSiguiente);
            fichaView.setPiezaSiguiente(piezaSiguiente);
        } else {
            if (tablero.posibleBajar("normal")) { //Si es posible bajar la pieza
                tablero.bajarPieza("normal", 1);
            } else {
                if (tablero.ocupadoPosPieza(tablero.getEnjuego())) { //Comprueba si es el final de la partida comprobando si hay otra pieza en su posición
                    if(!namePlayer.isEmpty()){
                        db.insertData(namePlayer,String.valueOf(puntuacion),String.valueOf(df.format(tiempoTranscurrido)));
                    }

                    try {
                        this.finalize();
                    } catch (Throwable e) {

                    }
                    mainActivity.pantallaGameOver();
                    stopMediaPlayer();
                } else {
                    tablero.asignarPieza(tablero.getEnjuego());
                    pieza = new Pieza(piezaSiguiente.getTipopieza(),tablero.getFilaInicial(), 0);
                    tablero.setEnjuego(pieza);
                    piezaSiguiente = generarPiezaSiguiente(); // Se genera una pieza aleatoria
                    tablero.setPiezaSiguiente(piezaSiguiente);
                    fichaView.setPiezaSiguiente(piezaSiguiente);
                }
            }
        }
    }

    public void controlarPiezaExtra(){
        int numPosiciones;
        //Se crea la pieza extra cada 30 segundos
        if (tablero.getPiezaExtra() == null) {

            if (tiempoTranscurrido > 0 && tiempoTranscurrido % 30000 == 0) {
                piezaExtra = generarPieza(3);
                tablero.setPiezaExtra(piezaExtra);
            }
        } else {
            if (tablero.posibleBajar("extra")) { //Si es posible bajar la pieza
                numPosiciones = tablero.numPosicionesBajar();
                tablero.bajarPieza("extra", numPosiciones);
            } else {
                if (tablero.ocupadoPosPieza(piezaExtra)) { //Comprueba si es el final de la partida comprobando si hay otra pieza en su posición
                    if(!namePlayer.isEmpty()){
                        db.insertData(namePlayer,String.valueOf(puntuacion),String.valueOf(df.format(tiempoTranscurrido)));
                    }

                    try {
                        this.finalize();
                    } catch (Throwable e) {

                    }
                    mainActivity.pantallaGameOver();
                    stopMediaPlayer();
                } else {
                    tablero.asignarPieza(piezaExtra);
                    piezaExtra = null;
                    tablero.setPiezaExtra(null);
                }
            }
        }
    }

    public void acortarTablero(){
        if ((tiempoTranscurrido > 0 && tiempoTranscurrido % 50000 == 0) || (pendienteAcortar)) {
            if(tablero.posibleAcortarTablero()){
                tablero.acortarTablero();
                pendienteAcortar = false;
            }else{
                pendienteAcortar = true;
            }

        }
    }

    public void cambiarColorPiezas(int lineasEliminadas){
        int colores[];
        if(lineasEliminadas == 1){
            colores = gameView.rellenarArrayColorFijo();
            fichaView.rellenarArrayColorFijo(colores);

        }else if(lineasEliminadas > 1){
            colores = gameView.rellenarArrayColoresAleatorios();
            fichaView.rellenarArrayColoresAleatorios(colores);

        }
    }

    public void finalizarTimer() {
        timer.cancel();
    }


    //Genera una nueva pieza con el desplazamiento de n columnas respecto de las coordenadas de la pieza
    public Pieza generarPieza(int desplazamientoColumnas) {
        int tipoPieza = (int) Math.floor(Math.random() * 6 + 1); //función para generar un numero aleatorio del 1 al 7
        Pieza pieza = new Pieza(tipoPieza, tablero.getFilaInicial(), desplazamientoColumnas);
        return pieza;
    }

    //Genera una nueva pieza con sus propias coordenadas
    public Pieza generarPiezaSiguiente(){
        int tipoPieza = (int) Math.floor(Math.random() * 6 + 1); //función para generar un numero aleatorio del 1 al 7
        Pieza pieza = new Pieza(tipoPieza);
        return pieza;
    }

    public void controlarMusica(){
        if(musica == null){
            playMediaPlayer(1);
        }else if (tiempoTranscurrido > 0 && tiempoTranscurrido % 20000 == 0) {
            stopMediaPlayer();
            if(cancion==4){
                cancion=1;
            }else{
                cancion++;
            }
            playMediaPlayer(cancion);
        }
    }

    public void  playMediaPlayer(int cancion){
        switch (cancion){
            case 1:
                musica= MediaPlayer.create(mainActivity, R.raw.centralcity);
                break;
            case 2:
                musica= MediaPlayer.create(mainActivity, R.raw.gameover);
                break;
            case 3:
                musica= MediaPlayer.create(mainActivity, R.raw.majorloss);
                break;
            case 4:
                musica= MediaPlayer.create(mainActivity, R.raw.neversurrender);
                break;
        }
        musica.start();
    }

    public void stopMediaPlayer(){
        musica.stop();
    }

    //Se controla la activación o desactivación del botón en función de la puntuación y de la limitación de usos por tiempo del botón
    public void controlarBotonRandom(){
        //Si el botón está desactivado
        if(!mainActivity.botonRandomActivado()){
            //Si se puede permitir la acción de cambiar la pieza y no tiene que esperar para poder usar el botón
            if(puntuacion >= 20 && (tiempoBotonDesactivado == -1 || tiempoBotonDesactivado == 40)){
                mainActivity.activarBotonPiezaRandom();
                tiempoBotonDesactivado =-1;
            }else{
                //Si se está esperando para poder usar el botón se incrementa el tiempo
                if(tiempoBotonDesactivado != -1 && tiempoBotonDesactivado < 40) {
                    tiempoBotonDesactivado++;
                }
            }
        }else{//Si el botón esta activado
            //Si no se puede cambiar la pieza debido a la puntuación, o hay que esperar para poder usar el botón
            if(puntuacion < 20 || (tiempoBotonDesactivado != -1 && tiempoBotonDesactivado < 40)){
                mainActivity.desactivarBotonPiezaRandom();
            }
        }

    }

    public void reducirPuntuacionPiezaRandom(){
        if(puntuacion >= 20) {
            puntuacion -= 20;
        }
    }

    public int getPuntuacion() {
        return puntuacion;
    }
    public int getTiempoTranscurrido() {
        return tiempoTranscurrido;
    }

    public void setTiempoBotonDesactivado(int tiempoBotonDesactivado) {
        this.tiempoBotonDesactivado = tiempoBotonDesactivado;
    }
    public void setPuntuacion(int aux){
        this.puntuacion = aux;
    }
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public int getTiempoBotonDesactivado() {
        return this.tiempoBotonDesactivado;
    }


}
