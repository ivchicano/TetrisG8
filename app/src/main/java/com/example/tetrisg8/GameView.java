package com.example.tetrisg8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.lang.reflect.Array;


public class GameView extends View {
    private Tablero tablero;
    private Paint p;
    private int[] arrayColores = {Color.RED, Color.WHITE, Color.MAGENTA, Color.BLUE, Color.GREEN, Color.GRAY, Color.CYAN, Color.YELLOW};
    private int[] arrayColoresAleatorios = {0, 0, 0, 0, 0, 0, 0, 0};

    public GameView(Context context, Tablero tablero) {
        super(context);
        this.tablero = tablero;
        p = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) { //Pinta la cuadrícula del tablero
        super.onDraw(canvas);
        pintarTablero(canvas);
        if (tablero.getEnjuego() != null) {
            pintarPieza(canvas);
            pintarPiezaExtra(canvas);
            pintarCuadricula(canvas);
        }

    }

    private void pintarTablero(Canvas canvas) {
        int anchoCelda = this.getWidth() / 10;
        int altoCelda = this.getHeight() / 20;
        int color = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                color = colorCelda(tablero.getTablero()[i][j]);
                p.setColor(color);
                canvas.drawRect(j * anchoCelda, i * altoCelda, j * anchoCelda + anchoCelda, i * altoCelda + altoCelda, p);
            }
        }
    }

    private void pintarCuadricula(Canvas canvas) {
        p.setColor(Color.GRAY);
        int anchoCelda = this.getWidth() / 10;
        int ejeX = 0;
        for (int i = 0; i < 9; i++) {
            ejeX += anchoCelda;
            canvas.drawLine(ejeX, 0, ejeX, this.getHeight(), p);
        }
        int altoCelda = this.getHeight() / 20;
        int ejeY = 0;
        for (int i = 0; i < 19; i++) {
            ejeY += altoCelda;
            canvas.drawLine(0, ejeY, this.getWidth(), ejeY, p);
        }
    }

    public void pintarPieza(Canvas canvas) {
        int anchoCelda = this.getWidth() / 10;
        int altoCelda = this.getHeight() / 20;
        int color = 0;
        color = colorCelda(tablero.getEnjuego().getTipopieza());
        p.setColor(color);
        int fila;
        int columna;
        int ite = 0;
        while (ite < 8) {
            fila = tablero.getEnjuego().getCuadrados()[ite];
            ite++;
            columna = tablero.getEnjuego().getCuadrados()[ite];
            ite++;
            canvas.drawRect(columna * anchoCelda, fila * altoCelda, columna * anchoCelda + anchoCelda, fila * altoCelda + altoCelda, p);
        }
    }

    public void pintarPiezaExtra(Canvas canvas) {
        if (tablero.getPiezaExtra() != null) {
            int anchoCelda = this.getWidth() / 10;
            int altoCelda = this.getHeight() / 20;
            int color = 0;
            color = colorCelda(tablero.getPiezaExtra().getTipopieza());
            p.setColor(color);
            int fila;
            int columna;
            int ite = 0;
            while (ite < 8) {
                fila = tablero.getPiezaExtra().getCuadrados()[ite];
                ite++;
                columna = tablero.getPiezaExtra().getCuadrados()[ite];
                ite++;
                canvas.drawRect(columna * anchoCelda, fila * altoCelda, columna * anchoCelda + anchoCelda, fila * altoCelda + altoCelda, p);
            }
        }
    }

    public int colorCelda(int codigo) {
        int color = 0;
        switch (codigo) {
            case -1:
                color = Color.DKGRAY;
                break;
            case 0:
                color = Color.BLACK;
                break;
            case 1:
                color = arrayColoresAleatorios[0];
                break;
            case 2:
                color = arrayColoresAleatorios[1];
                break;
            case 3:
                color = arrayColoresAleatorios[2];
                break;
            case 4:
                color = arrayColoresAleatorios[3];
                break;
            case 5:
                color = arrayColoresAleatorios[4];
                break;
            case 6:
                color = arrayColoresAleatorios[5];
                break;
            case 7:
                color = arrayColoresAleatorios[6];
                break;
            case 8:
                color = arrayColoresAleatorios[7];
                break;
        }
        return color;
    }

    public void rellenarArray(int gama) {
        if (gama == 1) {
            rellenarArrayColoresAleatorios();
        } else if (gama == 2) {
            rellenarArrayColorFijo();
        }
    }

    public int[] rellenarArrayColorFijo() {
        int indice;
        int color;
        indice = (int) Math.floor(Math.random() * 8);
        color = arrayColores[indice];
        for (int i = 0; i < arrayColoresAleatorios.length; i++) {
            arrayColoresAleatorios[i] = color;
        }
        return arrayColoresAleatorios;
    }

    public int[] rellenarArrayColoresAleatorios() {
        int indice;
        int color;
        for (int i = 0; i < arrayColoresAleatorios.length; i++) {
            indice = (int) Math.floor(Math.random() * 8);
            color = arrayColores[indice];
            while (colorAsignado(color, i)) {
                indice = (int) Math.floor(Math.random() * 8);
                color = arrayColores[indice];
            }
            arrayColoresAleatorios[i] = color;
        }
        return arrayColoresAleatorios;
    }

    public boolean colorAsignado(int color, int index) {
        boolean asignado = false;
        for (int i = 0; i < index; i++) {
            asignado = (arrayColoresAleatorios[i] == color);
            if (asignado) {
                return true;
            }
        }
        return asignado;
    }

    public int[] getArrayColoresAleatorios() {
        return arrayColoresAleatorios;
    }
}
