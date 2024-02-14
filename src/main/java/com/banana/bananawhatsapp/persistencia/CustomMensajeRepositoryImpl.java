package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.exceptions.MensajeException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
public class CustomMensajeRepositoryImpl implements CustomMensajeRepository{

    IMensajeRepository mensajeRepository;
    @Override
    public Mensaje crear(Mensaje mensaje) throws SQLException {
        return mensajeRepository.save(mensaje);
    }

    @Override
    public List<Mensaje> obtener(Usuario usuario) throws SQLException {
        return mensajeRepository.findAllById(usuario.getId());
    }

    @Override
    public boolean borrarEntre(Usuario remitente, Usuario destinatario) throws Exception {
        if(mensajeRepository.findAllByRemitenteAndDestinatario(remitente,destinatario).isEmpty()) {
            mensajeRepository.deleteAllByRemitenteAndDestinatario(remitente, destinatario);
            return true;
        }
        return false;
    }

    @Override
    public boolean borrarTodos(Usuario usuario) throws SQLException {
         if(mensajeRepository.findAllByRemitente(usuario)){
             mensajeRepository.deleteAllByRemitente(usuario);
             return true;
        }
        return false;
    }
}
