package com.example.tetrisg8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.core.content.ContextCompat;

//Vista que muestra la siguiente ficha

public class FichaView extends View {
    private Paint p;
    private Pieza piezaSiguiente;
    private int[] arrayColoresAleatorios;

    public FichaView(Context context) {
        super(context);
        p = new Paint();
        this.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkBlue));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(piezaSiguiente != null){
            pintarPieza(canvas);
        }

    }

    public void pintarPieza(Canvas canvas) {
        int anchoCelda = this.getWidth() / 5;
        int altoCelda = this.getHeight() / 10;
        int color = 0;
        color = colorCelda(piezaSiguiente.getTipopieza());
        p.setColor(color);
        int fila;
        int columna;
        int ite = 0;
        while (ite < 8) {
            fila = piezaSiguiente.getCuadrados()[ite];
            ite++;
            columna = piezaSiguiente.getCuadrados()[ite];
            ite++;
            //Deja 8 celdas vacías por arriba para aparecer centrada
            canvas.drawRect(columna * anchoCelda, fila * altoCelda, columna * anchoCelda + anchoCelda, fila * altoCelda + altoCelda, p);
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

    public void rellenarArray(int gama, int[] colores){
        if(gama == 1){
            rellenarArrayColoresAleatorios(colores);
        }else if(gama == 2){
            rellenarArrayColorFijo(colores);
        }
    }

    public void rellenarArrayColorFijo(int[] colores) {
        this.arrayColoresAleatorios = colores;
    }

    public void rellenarArrayColoresAleatorios(int[] colores) {
        this.arrayColoresAleatorios = colores;
    }

    public void setPiezaSiguiente(Pieza piezaSiguiente) {
        this.piezaSiguiente = piezaSiguiente;
    }

}

