package com.eventfy.EventoExistente.controller;

import com.eventfy.EventoExistente.dto.EventoDTO;
import com.eventfy.EventoExistente.entity.Evento;
import com.eventfy.EventoExistente.entity.Organizador; // Importando Organizador
import com.eventfy.EventoExistente.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eventos")
public class EventoExistenteController {

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping("/organizadorEvents")
    public List<EventoDTO> listarEventosPorOrganizador(@RequestParam Long idOrganizador) {
        List<Evento> eventos = eventoRepository.findByOrganizadorIdOrganizador(idOrganizador);

        return eventos.stream()
                .map(this::toDTO)  // Usando o método toDTO para cada evento
                .collect(Collectors.toList());
    }

    // Método para converter Evento para EventoDTO
    private EventoDTO toDTO(Evento evento) {
        EventoDTO dto = new EventoDTO();

        // Mapeando os campos do Evento para o EventoDTO
        dto.setNomeEvento(evento.getNomeEvento());
        dto.setDataEvento(evento.getDataEvento());
        dto.setHorarioInicio(evento.getHorarioInicio());
        dto.setHorarioFim(evento.getHorarioFim());
        dto.setLocalEvento(evento.getLocalEvento());
        dto.setTipoEvento(evento.getTipoEvento());

        // Verificando o Organizador e extraindo seu ID
        dto.setIdOrganizador(Optional.ofNullable(evento.getOrganizador())
                .map(Organizador::getIdOrganizador)  // Extrai o ID do Organizador, caso exista
                .orElse(null));  // Se o Organizador for null, retorna null

        // Mapeando os campos booleanos
        dto.setIncluirTarefas(evento.isIncluirTarefas());
        dto.setListaConvidados(evento.isListaConvidados());
        dto.setFornecedores(evento.isFornecedores());

        return dto;
    }
}
