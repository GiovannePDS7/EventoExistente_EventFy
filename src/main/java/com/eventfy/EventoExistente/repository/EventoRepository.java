package com.eventfy.EventoExistente.repository;


import com.eventfy.EventoExistente.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository <Evento, Long> {
}
