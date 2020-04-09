package com.example.tetrisg8;

import android.widget.TextView;

import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.junit.Test;

import static org.junit.Assert.*;

public class MensajeLineaTest {

    private TextView mockedTextView = Mockito.mock(TextView.class);
    @Test
    public void mostrarMensaje() {
        MensajeLinea mensajeLinea = new MensajeLinea(mockedTextView);
        String[] mensajesEsperados = {
                "Bien!",
                "Buen trabajo!",
                "Sigue así!",
                "Ánimo!",
                "Tu puedes!"
        };
        InOrder inOrder = Mockito.inOrder(mockedTextView);
        for (String mensajesEsperado : mensajesEsperados) {
            String mensaje = mensajeLinea.siguienteMensaje();
            assertEquals(mensajesEsperado, mensaje);
            inOrder.verify(mockedTextView).setText(mensajesEsperado);
        }
        String mensajeInicial = mensajeLinea.siguienteMensaje();
        assertEquals(mensajesEsperados[0], mensajeInicial);
        inOrder.verify(mockedTextView).setText(mensajesEsperados[0]);
        Mockito.verifyNoMoreInteractions(mockedTextView);
    }
}