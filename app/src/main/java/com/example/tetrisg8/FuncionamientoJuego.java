package com.example.tetrisg8;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FuncionamientoJuego {

    private MainActivity mainActivity;

    private GameView gameView;
    private FichaView fichaView;

    private Timer timer;
    private int tiempoBotonDesactivado = -1;
    private int periodo = 1000;

    private Tablero tablero;
    private boolean pendienteAcortar = false;
    private Pieza piezaExtra;
    private Pieza piezaSiguiente;
    private int puntuacion;
    private int lineasEliminadas;
    private int tiempoTranscurrido;

    private String namePlayer;
    private DatabaseClass db;
    private DateFormat df;

    private ControlarMusica controlarMusica;

    public FuncionamientoJuego(GameView gameView, FichaView fichaView, Tablero tab, String namePlayer, Context context) {
        this.gameView = gameView;
        this.fichaView = fichaView;
        tablero = tab;
        mainActivity = (MainActivity) context;
        controlarMusica = new ControlarMusica(mainActivity);
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

                controlarMusica.controlarMusica(tiempoTranscurrido);
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

    public void inicializarPartida() {
        puntuacion = 0;
        tiempoTranscurrido = 0;
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(mainActivity);
        int gamaColores = Integer.parseInt(pref.getString("lista", "1"));
        gameView.rellenarArray(gamaColores);
        int[] colores = gameView.getArrayColoresAleatorios();
        fichaView.rellenarArray(gamaColores, colores);
    }

    public void controlarPiezaNormal() {
        df = new SimpleDateFormat("mm:ss");
        if (tablero.getEnjuego() == null) {
            Pieza pieza = generarPieza(0);
            tablero.setEnjuego(pieza);
            piezaSiguiente = generarPiezaSiguiente();
            tablero.setPiezaSiguiente(piezaSiguiente);
            fichaView.setPiezaSiguiente(piezaSiguiente);
        } else {
            if (tablero.posibleBajar("normal")) {
                tablero.bajarPieza("normal", 1);
            } else {
                if (tablero.ocupadoPosPieza(tablero.getEnjuego())) {
                    if (!namePlayer.isEmpty()) {
                        db.insertData(namePlayer,String.valueOf(puntuacion),String.valueOf(df.format(tiempoTranscurrido)));
                    }
                    mainActivity.pantallaGameOver();
                    controlarMusica.stopMediaPlayer();
                } else {
                    tablero.asignarPieza(tablero.getEnjuego());
                    Pieza pieza = new Pieza(piezaSiguiente.getTipopieza(),tablero.getFilaInicial(), 0);
                    tablero.setEnjuego(pieza);
                    piezaSiguiente = generarPiezaSiguiente();
                    tablero.setPiezaSiguiente(piezaSiguiente);
                    fichaView.setPiezaSiguiente(piezaSiguiente);
                }
            }
        }
    }

    public void controlarPiezaExtra() {
        int numPosiciones;
        if (tablero.getPiezaExtra() == null) {
            if (tiempoTranscurrido > 0 && tiempoTranscurrido % 30000 == 0) {
                piezaExtra = generarPieza(3);
                tablero.setPiezaExtra(piezaExtra);
            }
        } else {
            if (tablero.posibleBajar("extra")) {
                numPosiciones = tablero.numPosicionesBajar();
                tablero.bajarPieza("extra", numPosiciones);
            } else {
                if (tablero.ocupadoPosPieza(piezaExtra)) {
                    if (!namePlayer.isEmpty()) {
                        db.insertData(namePlayer,String.valueOf(puntuacion),String.valueOf(df.format(tiempoTranscurrido)));
                    }
                    mainActivity.pantallaGameOver();
                    controlarMusica.stopMediaPlayer();
                } else {
                    tablero.asignarPieza(piezaExtra);
                    piezaExtra = null;
                    tablero.setPiezaExtra(null);
                }
            }
        }
    }

    public void acortarTablero() {
        if ((tiempoTranscurrido > 0 && tiempoTranscurrido % 50000 == 0) || (pendienteAcortar)) {
            if (tablero.posibleAcortarTablero()) {
                tablero.acortarTablero();
                pendienteAcortar = false;
            } else {
                pendienteAcortar = true;
            }
        }
    }

    public void cambiarColorPiezas(int lineasEliminadas) {
        int[] colores;
        if (lineasEliminadas == 1) {
            colores = gameView.rellenarArrayColorFijo();
            fichaView.rellenarArrayColorFijo(colores);
        } else if (lineasEliminadas > 1) {
            colores = gameView.rellenarArrayColoresAleatorios();
            fichaView.rellenarArrayColoresAleatorios(colores);
        }
    }

    public void finalizarTimer() {
        timer.cancel();
        controlarMusica.stopMediaPlayer();
    }

    public Pieza generarPieza(int desplazamientoColumnas) {
        int tipoPieza = (int) Math.floor(new Random().nextInt(6) + 1);
        return new Pieza(tipoPieza, tablero.getFilaInicial(), desplazamientoColumnas);
    }

    public Pieza generarPiezaSiguiente() {
        int tipoPieza = (int) Math.floor(new Random().nextInt(6) + 1);
        return new Pieza(tipoPieza);
    }

    public void controlarBotonRandom() {
        if (!mainActivity.botonRandomActivado()) {
            if (puntuacion >= 20 && (tiempoBotonDesactivado == -1 || tiempoBotonDesactivado == 40)) {
                mainActivity.activarBotonPiezaRandom();
                tiempoBotonDesactivado = -1;
            } else {
                if (tiempoBotonDesactivado != -1 && tiempoBotonDesactivado < 40) {
                    tiempoBotonDesactivado++;
                }
            }
        } else {
            if (puntuacion < 20 || (tiempoBotonDesactivado != -1 && tiempoBotonDesactivado < 40)) {
                mainActivity.desactivarBotonPiezaRandom();
            }
        }

    }

    public void reducirPuntuacionPiezaRandom() {
        if (puntuacion >= 20) {
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

}
