package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Set;
@Service
public class ServicioUsuarios implements IServicioUsuarios{

     @Autowired
    private IUsuarioRepository userRepo;
    @Override
    public Usuario obtener(Integer id) throws UsuarioException {
        Usuario user = userRepo.findById(id).orElseThrow(() -> new RuntimeException());
        return user;
    }

    @Override
    @Transactional
    public Usuario crearUsuario(Usuario usuario) throws UsuarioException, SQLException {
        userRepo.crear(usuario);
        return null;
    }

    @Override
    public boolean borrarUsuario(Usuario usuario) throws UsuarioException, SQLException {
        userRepo.borrar(usuario);
        return false;
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) throws UsuarioException {
        return null;
    }

    @Override
    public Set<Usuario> obtenerPosiblesDesinatarios(Usuario usuario, int max) throws UsuarioException {
        return null;
    }
}
