package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.config.SpringConfig;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.util.DBUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
class ServicioMensajeriaTest {
    @Autowired
    IServicioUsuarios servicioUsuario;
    @Autowired
    IServicioMensajeria servicioMensajeria;

    @BeforeEach
    void cleanAndReloadData() {
        DBUtil.reloadDB();
    }

    @Test
    void dadoRemitenteYDestinatarioYTextoValido_cuandoEnviarMensaje_entoncesMensajeValido() throws Exception {
        Usuario remitente = servicioUsuario.obtener(1);
        Usuario destinatario = servicioUsuario.obtener(2);
        String texto = "Felices Fiestas!";
        Mensaje message = servicioMensajeria.enviarMensaje(remitente, destinatario, texto);
        assertThat(message.getId(), greaterThan(0));
    }

    @Test
    void dadoRemitenteYDestinatarioYTextoNOValido_cuandoEnviarMensaje_entoncesExcepcion() throws Exception {
        Usuario remitente = servicioUsuario.obtener(1);
        Usuario destinatario = servicioUsuario.obtener(2);
        String texto = "SMS < 10";
        assertThrows(Exception.class, () -> {
            servicioMensajeria.enviarMensaje(remitente, destinatario, texto);
        });
    }


    @Test
    void dadoRemitenteYDestinatarioValido_cuandoMostrarChatConUsuario_entoncesListaMensajes() throws Exception {
        Usuario remitente = servicioUsuario.obtener(1);
        Usuario destinatario = servicioUsuario.obtener(2);

        List<Mensaje> userMessages = servicioMensajeria.mostrarChatConUsuario(remitente, destinatario);
        assertNotNull(userMessages);
    }

    @Test
    void dadoRemitenteYDestinatarioNOValido_cuandoMostrarChatConUsuario_entoncesExcepcion() throws Exception {
        Usuario remitente = servicioUsuario.obtener(1);
        Usuario destinatario = new Usuario(2, null, null, null, false);
        assertThrows(Exception.class, () -> {
            List<Mensaje> userMessages = servicioMensajeria.mostrarChatConUsuario(remitente, destinatario);
        });
    }

    @Test
    void dadoRemitenteYDestinatarioValido_cuandoBorrarChatConUsuario_entoncesOK() throws Exception {
        Usuario remitente = servicioUsuario.obtener(1);
        Usuario destinatario = servicioUsuario.obtener(2);
        boolean borrarChat = servicioMensajeria.borrarChatConUsuario(remitente, destinatario);
        assertTrue(borrarChat);
    }

    @Test
    void dadoRemitenteYDestinatarioNOValido_cuandoBorrarChatConUsuario_entoncesExcepcion() throws Exception {
        Usuario remitente = servicioUsuario.obtener(1);
        Usuario destinatario = new Usuario(2, null, null, null, false);
        assertThrows(Exception.class, () -> {
            boolean borrarChat = servicioMensajeria.borrarChatConUsuario(remitente, destinatario);
        });
    }
}