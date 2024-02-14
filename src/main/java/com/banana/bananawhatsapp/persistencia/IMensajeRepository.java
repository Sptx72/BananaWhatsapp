package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMensajeRepository extends JpaRepository<Mensaje,Integer>, CustomMensajeRepository {

    List<Mensaje> findAllById(Integer id);

    void deleteAllByRemitenteAndDestinatario(Usuario remitente, Usuario destinatario);

    List<Mensaje> findAllByRemitenteAndDestinatario(Usuario remitente, Usuario destinatario);

    void deleteAllByRemitente(Usuario usuario);

    boolean findAllByRemitente(Usuario usuario);
}
