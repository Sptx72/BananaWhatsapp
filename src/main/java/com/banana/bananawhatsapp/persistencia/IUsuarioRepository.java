package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.modelos.Usuario;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Integer>, CustomUsuarioRepository {
   List<Usuario> findByIdStartingWith(Integer id);
}