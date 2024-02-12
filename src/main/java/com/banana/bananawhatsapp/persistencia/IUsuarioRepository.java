package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Set;

@Repository
public interface IUsuarioRepository  extends JpaRepository<Usuario, Integer> {

    @Query(value = "select * from usuario where id = ?1", nativeQuery = true)
    public Usuario obtener(Integer id) throws SQLException;

    @Query(value = "select * from usuario where id != ?1 limit ?2", nativeQuery = true)
    public Set<Usuario> obtenerPosiblesDestinatarios(Integer id, Integer max) throws SQLException;

}
