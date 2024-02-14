package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.exceptions.MensajeException;
import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.IMensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
@Service
public class ServicioMensajeria implements IServicioMensajeria{
    @Autowired
    IMensajeRepository mensajeRepo;

    @Override
    @Transactional
    public Mensaje enviarMensaje(Usuario remitente, Usuario destinatario, String texto) throws UsuarioException, MensajeException, SQLException {
        remitente.valido();
        destinatario.valido();

        Mensaje mensaje = new Mensaje();
        mensaje.setRemitente(remitente);
        mensaje.setDestinatario(destinatario);
        mensaje.setCuerpo(texto);
        mensaje.setFecha(LocalDate.now());

        mensaje.valido();

        return mensajeRepo.save(mensaje);
    }

    @Override
    public List<Mensaje> mostrarChatConUsuario(Usuario remitente, Usuario destinatario) throws UsuarioException, MensajeException, SQLException {
        remitente.valido();
        destinatario.valido();
        return mensajeRepo.findAllByRemitenteAndDestinatario(remitente, destinatario);
    }

    @Override
    @Transactional
    public boolean borrarChatConUsuario(Usuario remitente, Usuario destinatario) throws UsuarioException, MensajeException {
        remitente.valido();
        destinatario.valido();

        mensajeRepo.deleteAllByRemitenteAndDestinatario(remitente, destinatario);

        return true;
    }
}
