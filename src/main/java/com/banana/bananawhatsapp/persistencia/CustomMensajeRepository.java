package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
@Repository
public interface CustomMensajeRepository {

    public Mensaje crear(Mensaje mensaje) throws SQLException;

    public List<Mensaje> obtener(Usuario usuario) throws SQLException;

    public boolean borrarEntre(Usuario remitente, Usuario destinatario) throws Exception;

    public boolean borrarTodos(Usuario usuario) throws SQLException;
}
