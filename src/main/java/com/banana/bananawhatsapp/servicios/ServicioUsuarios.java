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
    IUsuarioRepository userRepo;
    @Override
    public Usuario obtener(Integer id) throws UsuarioException {
        Usuario user = userRepo.findById(id).orElseThrow(() -> new RuntimeException());
        return user;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) throws UsuarioException, SQLException {
        usuario.valido();
        return userRepo.save(usuario);
    }

    @Override
    public boolean borrarUsuario(Usuario usuario) throws UsuarioException, SQLException {
        usuario.valido();
        userRepo.delete(usuario);
        return userRepo.findById(usuario.getId()).isEmpty();
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) throws UsuarioException {
        usuario.valido();
        return userRepo.save(usuario);
    }

    @Override
    public Set<Usuario> obtenerPosiblesDesinatarios(Usuario usuario, int max) throws UsuarioException, SQLException {
        usuario.valido();
        return userRepo.obtenerPosiblesDestinatarios(usuario.getId(), max);
    }
}
