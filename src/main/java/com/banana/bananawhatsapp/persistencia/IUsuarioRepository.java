package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Set;
@Repository
public interface IUsuarioRepository  extends JpaRepository<Usuario, Integer> {
    public Usuario obtener(Integer id) throws SQLException;
    public Usuario crear(Usuario usuario) throws SQLException;

    public Usuario actualizar(Usuario usuario) throws SQLException;

    public boolean borrar(Usuario usuario) throws SQLException;

    public Set<Usuario> obtenerPosiblesDestinatarios(Integer id, Integer max) throws SQLException;

}
