package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
@Repository
public class CustomUsuarioRepositoryImpl implements CustomUsuarioRepository{
    IUsuarioRepository usuarioRepository;

    @Override
    public Usuario obtener(Integer id) throws SQLException {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioException());
        return usuario;
    }

    @Override
    public Usuario crear(Usuario usuario) throws SQLException {
         Usuario usuarioCreado = usuarioRepository.save(usuario);
        return usuarioCreado;
    }

    @Override
    public Usuario actualizar(Usuario usuario) throws SQLException {
        Usuario usuarioactualizado = usuarioRepository.save(usuario);
        return usuarioactualizado;
    }

    @Override
    public boolean borrar(Usuario usuario) throws SQLException {

        if(usuarioRepository.existsById(usuario.getId())){
            usuarioRepository.delete(usuario);
            return true;
        }
        return false;
    }

    @Override
    public Set<Usuario> obtenerPosiblesDestinatarios(Integer id, Integer max) throws SQLException {
        List<Usuario> usuarios = usuarioRepository.findByIdStartingWith(id);
        Set<Usuario> usuariosMax= usuarios.stream().limit(max).collect(Collectors.toSet());
        return usuariosMax;
    }
}
