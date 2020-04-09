package com.example.tetrisg8;

import android.widget.TextView;

public class MensajeLinea {

    private TextView cajaMensajeLinea;
    private String[] mensajesEsperados = {
            "Bien!",
            "Buen trabajo!",
            "Sigue así!",
            "Ánimo!",
            "Tu puedes!"
    };
    private int proximoMensaje = 0;

    public MensajeLinea(TextView cajaMensajeLinea) {
        this.cajaMensajeLinea = cajaMensajeLinea;
    }

    public String siguienteMensaje() {
        String mensaje = mensajesEsperados[proximoMensaje];
        cajaMensajeLinea.setText(mensaje);
        if (proximoMensaje < mensajesEsperados.length - 1) {
            proximoMensaje++;
        } else {
            proximoMensaje = 0;
        }
        return mensaje;
    }
}
