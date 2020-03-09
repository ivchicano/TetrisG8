package com.example.tetrisg8;

import android.media.MediaPlayer;

public class ControlarMusica {

    private MainActivity mainActivity;

    private MediaPlayer musica;
    private int cancion = 1;

    private int[] canciones = {R.raw.centralcity, R.raw.gameover, R.raw.majorloss, R.raw.neversurrender};

    public ControlarMusica(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void controlarMusica(int tiempoTranscurrido) {
        if (musica == null) {
            playMediaPlayer(1);
        } else if (tiempoTranscurrido > 0 && tiempoTranscurrido % 20000 == 0) {
            stopMediaPlayer();
            if (cancion == 4) {
                cancion = 1;
            } else {
                cancion++;
            }
            playMediaPlayer(cancion);
        }
    }

    public void  playMediaPlayer(int cancion) {
        musica = MediaPlayer.create(mainActivity, canciones[cancion - 1]);
        musica.start();
    }

    public void stopMediaPlayer() {
        musica.stop();
    }

}
