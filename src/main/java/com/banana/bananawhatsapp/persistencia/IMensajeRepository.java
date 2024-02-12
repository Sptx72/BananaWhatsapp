package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface IMensajeRepository extends JpaRepository<Mensaje,Integer> {

    @Query(value = "select * from mensaje where from_user = ?1", nativeQuery = true)
    public List<Mensaje> obtener(Integer usuarioId) throws SQLException;

    @Query(value = "select * from mensaje where from_user = ?1 and to_user = ?1", nativeQuery = true)
    List<Mensaje> getBetween(Integer userId, Integer destinationId);

    @Modifying
    @Transactional
    @Query(value = "delete from mensaje where from_user = ?1", nativeQuery = true)
    void borrarTodos(Integer usuarioId) throws SQLException;

    @Modifying
    @Transactional
    @Query(value = "delete from mensaje where from_user = ?1 and to_user = ?2", nativeQuery = true)
    void deleteMessagesBetweenUsers(Integer senderId, Integer destinationId);
}
