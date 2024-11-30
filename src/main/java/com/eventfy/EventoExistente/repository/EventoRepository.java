package com.eventfy.EventoExistente.repository;


import com.eventfy.EventoExistente.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository <Evento, Long> {
    List<Evento> findByOrganizadorIdOrganizador(Long idOrganizador);
}
