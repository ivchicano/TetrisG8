package com.example.tetrisg8;


import android.graphics.Matrix;

public class Tablero {

    private int tablero[][] = new int[20][10];  //Tablero que almacenará las piezas
    private Pieza enjuego;
    private Pieza piezaExtra;
    private Pieza piezaSiguiente;
    private int filaInicial;

    public void inicializarTablero() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                tablero[i][j] = 0;
            }
        }
        filaInicial = 0;
    }

    public void asignarPieza(Pieza pieza) {
        for (int i = 0; i < 8; i += 2) {
            int fila = pieza.getCuadrados()[i];
            int columna = pieza.getCuadrados()[i + 1];

            tablero[fila][columna] = pieza.getTipopieza();
        }
    }

    public boolean ocupadoPosPieza(Pieza pieza) {
        boolean ocupado = false;
        for (int i = 0; i < 8; i += 2) {
            if (tablero[pieza.getCuadrados()[i]][pieza.getCuadrados()[i + 1]] != 0) {
                ocupado = true;
            }
        }
        return ocupado;
    }

    //Bajar la pieza el número de celdas recibidas
    public void bajarPieza(String tipoPieza, int celdasBajar) {
        int i = 0;
        while (i < 8) {
            switch (tipoPieza) {
                case "normal":
                    this.enjuego.getCuadrados()[i] += celdasBajar;

                    break;
                case "extra":
                    this.piezaExtra.getCuadrados()[i] += celdasBajar;
                    break;
            }
            i += 2;
        }
    }

    //Comprueba si es posible bajar las piezas
    public boolean posibleBajar(String tipoPieza) {
        int filaSiguiente;
        boolean esposible = true;
        int i = 0;
        //Comprueba si es posible (si existe la celda y si esta vacia)
        while (esposible && i < 8) {
            switch (tipoPieza) {
                case "normal":
                    filaSiguiente = enjuego.getCuadrados()[i] + 1;
                    esposible = (filaSiguiente < 20) && (tablero[filaSiguiente][enjuego.getCuadrados()[i + 1]] == 0);
                    break;
                case "extra":
                    filaSiguiente = piezaExtra.getCuadrados()[i] + 1;
                    esposible = (filaSiguiente < 20) && (tablero[filaSiguiente][piezaExtra.getCuadrados()[i + 1]] == 0);
                    break;
            }
            i += 2;
        }
        return esposible;
    }

    //Devuelve el número de posiciones que se puede bajar una pieza extra: 2 (por defecto) o 1
    public int numPosicionesBajar() {
        int numeroCasillas = 0;
        int filaSiguiente;
        int i = 0;
        while ((i < 8) && (numeroCasillas != 1)) {
            filaSiguiente = piezaExtra.getCuadrados()[i] + 1;
            if ((filaSiguiente < 19) && (tablero[filaSiguiente + 1][piezaExtra.getCuadrados()[i + 1]] == 0)) {
                numeroCasillas = 2;
            } else {
                numeroCasillas = 1;
            }
            i += 2;
        }
        return numeroCasillas;
    }


    //Mover pieza a la izquierda
    public boolean izquierda() {
        int columna = 0;
        boolean esposible = true;
        int i = 0;
        //Comprueba si es posible (si existe la celda y si esta vacia)
        while (esposible && i < 8) {
            columna = enjuego.getCuadrados()[i + 1] - 1;
            esposible = (columna > -1 && columna < 11) && (tablero[enjuego.getCuadrados()[i]][columna] == 0);
            i += 2;
        }

        if (esposible) {
            //Si ha sido posible realiza el cambio en la pieza en juego
            i = 1;
            while (i < 8) {
                this.enjuego.getCuadrados()[i]--;
                i += 2;
            }
        }
        return esposible;
    }

    //Mover pieza a la derecha
    public boolean derecha() {
        int columna = 0;
        boolean esposible = true;
        int i = 0;
        //Comprueba si es posible (si existe la celda y si esta vacia)
        while (esposible && i < 8) {
            columna = enjuego.getCuadrados()[i + 1] + 1;
            esposible = (columna > -1 && columna < 10) && (tablero[enjuego.getCuadrados()[i]][columna] == 0);
            i += 2;
        }

        if (esposible) {
            //Si ha sido posible realiza el cambio en la pieza en juego
            i = 1;
            while (i < 8) {
                this.enjuego.getCuadrados()[i]++;
                i += 2;
            }
        }
        return esposible;
    }

    //Rotar pieza
    public boolean rotar(int grados) {
        //Si es la pieza es cuadrada no se rota
        if (enjuego.getTipopieza() == 8 || enjuego.getTipopieza() == 4)
            return false;
        //Coordenadas del ultimo cuadrado de la pieza
        int fila = enjuego.getCuadrados()[6];
        int columna = enjuego.getCuadrados()[7];
        //cuadrados representados como puntos en una matriz
        Matrix matriz = new Matrix();
        matriz.setRotate(grados, fila, columna);
        float[] puntos = new float[]{0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < 8; i++) {
            puntos[i] = enjuego.getCuadrados()[i];
        }
        matriz.mapPoints(puntos);
        //comprobar si es posible
        boolean esposible = true;
        int j = 0;
        while (esposible && j < 8) {
            fila = (int) puntos[j];
            columna = (int) puntos[j + 1];
            //si no excede los limites y la posicion esta vacia
            esposible = (columna >= 0) && (columna < 10) && (fila >= 0) && (tablero[fila][columna] == 0);
            j += 2;
        }
        //si ha sido posible guarda las nuevas coordenadas en pieza en juego
        if (esposible) {
            for (int i = 0; i < 8; i++) {
                enjuego.getCuadrados()[i] = (int) puntos[i];
            }
        }
        return esposible;
    }

    public int[][] getTablero() {
        return tablero;
    }

    public Pieza getEnjuego() {
        return enjuego;
    }

    public void setEnjuego(Pieza enjuego) {
        this.enjuego = enjuego;
    }

    public int getFilaInicial() {
        return filaInicial;
    }

    //comprobamos todas las filas para ver si alguna esté completa y devuelve el número de filas completas, eliminandolas
    public int lineasCompletas() {
        int nLineasCompletas = 0;
        boolean lineaCompleta;


        for (int i = filaInicial+1; i < 20; i++) {
            lineaCompleta = true;
            for (int j = 0; j < 10; j++) {
                if (tablero[i][j] == 0 || tablero[i][j] == -1) {
                    lineaCompleta = false;
                }
            }
            if (lineaCompleta) {
                nLineasCompletas++;
                bajarLineas(i);
            }

        }

        return nLineasCompletas;

    }


    public void bajarLineas(int filaEliminar) {
        int filaAuxEliminada[];
        int filaAuxSuperior[] = copiarFila(filaInicial);
        for (int i = filaInicial+1; i <= filaEliminar; i++) { //Baja todas las filas una posición menos la primera hasta la linea a eliminar
            filaAuxEliminada = copiarFila(i);
            for (int j = 0; j < 10; j++) {
                tablero[i][j] = filaAuxSuperior[j];
            }
            filaAuxSuperior = filaAuxEliminada;
        }

        for (int j = 0; j < 10; j++) { //Pone a 0 la primera fila
            tablero[filaInicial][j] = 0;
        }
    }

    public int[] copiarFila(int fila){
        int aux[] = new int[10];
        for (int j = 0; j < 10; j++) {
            aux[j] = tablero[fila][j];
        }
        return aux;
    }


    public void acortarTablero() {
        int filaLimite = filaInicial + 2;
        for (int i = filaInicial; i < filaLimite; i++) {
            for (int j = 0; j < 10; j++) {
                tablero[i][j] = -1;
            }
        }
        filaInicial = filaLimite;
    }

    public boolean posibleAcortarTablero() {
        boolean posible = true;
        int i = 0;
        while (i < 8 && posible) {
            if (piezaExtra == null) {
                posible = !((enjuego.getCuadrados()[i] == filaInicial) || (enjuego.getCuadrados()[i] == filaInicial + 1));
            } else {
                posible = !((enjuego.getCuadrados()[i] == filaInicial) || (enjuego.getCuadrados()[i] == filaInicial + 1) || (piezaExtra.getCuadrados()[i] == filaInicial) || (piezaExtra.getCuadrados()[i] == filaInicial));
            }
            i += 2;
        }
        return posible;
    }

    public void setPiezaExtra(Pieza piezaExtra) {
        this.piezaExtra = piezaExtra;
    }

    public Pieza getPiezaExtra() {
        return piezaExtra;
    }

    public void setPiezaSiguiente(Pieza piezaSiguiente) {
        this.piezaSiguiente = piezaSiguiente;
    }

}
