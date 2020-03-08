package com.example.tetrisg8;

import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class FuncionamientoJuegoTest {

    private GameView gameView = Mockito.mock(GameView.class);
    private FichaView fichaView = Mockito.mock(FichaView.class);
    private Tablero tablero = Mockito.mock(Tablero.class);
    private FuncionamientoJuego funcionamientoJuego;
    private MainActivity mainActivity = Mockito.mock(MainActivity.class);

    @Before
    public void setUp() {
        funcionamientoJuego = new FuncionamientoJuego(gameView, fichaView, tablero, "Juan", null);
        funcionamientoJuego.setMainActivity(mainActivity);

    }

    @Test
    public void testMultipleFunctionality() {
        assertEquals(0, funcionamientoJuego.getTiempoTranscurrido());
        assertEquals(0, funcionamientoJuego.getPuntuacion());

        funcionamientoJuego.setPuntuacion(1000);
        funcionamientoJuego.reducirPuntuacionPiezaRandom();
        assertEquals(980, funcionamientoJuego.getPuntuacion());

        Mockito.when(mainActivity.botonRandomActivado()).thenReturn(false);
        funcionamientoJuego.controlarBotonRandom();
        assertEquals(-1, funcionamientoJuego.getTiempoBotonDesactivado());

        funcionamientoJuego.setTiempoBotonDesactivado(32);
        funcionamientoJuego.controlarBotonRandom();
        assertEquals(33, funcionamientoJuego.getTiempoBotonDesactivado());

    }

}