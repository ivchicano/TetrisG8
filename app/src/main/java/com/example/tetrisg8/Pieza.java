package com.example.tetrisg8;


public class Pieza {
    private int tipopieza; // Variable que determinará el tipo de pieza
    //posiciones de los cuadrados que forman la pieza(xfila,ycolumna):X1,Y1,...,X4,Y4
    private int[] cuadrados;


    //Si es una pieza extra se genera desplazada 3 columnas hacia la izquierda

    public Pieza(int tipo, int despFila, int despCol) {
        tipopieza = tipo;
        //Posicion inicial de las celdas en funcion del tipo
        switch (tipo) {
            case 1://tipo I
                cuadrados = new int[]{0+despFila, 3 + despCol, 1+despFila, 3 + despCol, 2+despFila, 3 + despCol, 3+despFila, 3 + despCol};
                break;
            case 2://tipo J
                cuadrados = new int[]{2+despFila, 3 + despCol, 0+despFila, 4 + despCol, 1+despFila, 4 + despCol, 2+despFila, 4 + despCol};
                break;
            case 3://tipo L
                cuadrados = new int[]{0+despFila, 3 + despCol, 1+despFila, 3 + despCol, 2+despFila, 3 + despCol, 2+despFila, 4 + despCol};
                break;
            case 4://tipo O
                cuadrados = new int[]{0+despFila, 3 + despCol, 1+despFila, 3 + despCol, 0+despFila, 4 + despCol, 1+despFila, 4 + despCol};
                break;
            case 5://tipo S
                cuadrados = new int[]{1+despFila, 3 + despCol, 0+despFila, 4 + despCol, 1+despFila, 4 + despCol, 0+despFila, 5 + despCol};
                break;
            case 6://tipo T
                cuadrados = new int[]{0+despFila, 3 + despCol, 0+despFila, 4 + despCol, 1+despFila, 4 + despCol, 0+despFila, 5 + despCol};
                break;
            case 7://tipo Z
                cuadrados = new int[]{0+despFila, 3 + despCol, 0+despFila, 4 + despCol, 1+despFila, 4 + despCol, 1+despFila, 5 + despCol};
                break;
            case 8://tipo .
                cuadrados = new int[]{0+despFila, 3 + despCol, 0+despFila, 3 + despCol, 0+despFila, 3 + despCol, 0+despFila, 3 + despCol};
                break;

        }
    }

    //Constructora para las piezas siguientes, que deben mostrarse diferentes
    public Pieza(int tipo){
        tipopieza = tipo;
        //Posicion inicial de las celdas en funcion del tipo
        switch (tipo) {
            //{fila,columna}
            case 1://tipo I
                cuadrados = new int[]{3, 3, 4, 3, 5, 3, 6, 3};
                break;
            case 2://tipo J
                cuadrados = new int[]{6, 1, 4, 2, 5, 2, 6, 2};
                break;
            case 3://tipo L
                cuadrados = new int[]{4, 2, 5, 2, 6, 2, 6, 3};
                break;
            case 4://tipo O
                cuadrados = new int[]{4, 1, 5, 1, 4, 2, 5, 2};
                break;

            case 5://tipo S
                cuadrados = new int[]{5, 1, 4, 2, 5, 2, 4, 3};
                break;
            case 6://tipo T
                cuadrados = new int[]{4, 1, 4, 2, 5, 2, 4, 3};
                break;
            case 7://tipo Z
                cuadrados = new int[]{4, 1, 4, 2, 5, 2, 5, 3};
                break;
            case 8://tipo .
                cuadrados = new int[]{4, 1, 4, 1, 4, 1, 4, 1};
                break;
        }
    }


    public int[] getCuadrados() {
        return cuadrados;
    }

    public int getTipopieza() {
        return tipopieza;
    }
}
