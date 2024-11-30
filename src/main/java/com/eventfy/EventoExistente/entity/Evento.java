package com.eventfy.EventoExistente.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;



@Getter
@Setter
@Data
@Table(name = "Evento")
@Entity

public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Evento")
    private Long idEvento;

    @Column(name = "Nome_Evento", nullable = false, length = 100)
    private String nomeEvento;

    @Column(name = "Data_Evento", nullable = false)
    private LocalDate dataEvento;

    @Column(name = "Horario_Inicio", nullable = false)
    private LocalTime horarioInicio;

    @Column(name = "Horario_Fim", nullable = false)
    private LocalTime horarioFim;

    @Column(name = "Local_Evento", nullable = false, length = 150)
    private String localEvento;

    @Column(name = "Tipo_Evento", length = 50)
    private String tipoEvento;

    @ManyToOne
    @JoinColumn(name = "Id_Organizador", referencedColumnName = "Id_Organizador")
    private Organizador organizador; // A referência ao Organizador é mapeada aqui

    @Lob
    @Column(name = "Foto_Evento")
    private byte[] fotoEvento;

    @Column(name = "Incluir_Tarefas", nullable = false)
    private boolean incluirTarefas = false;

    @Column(name = "Lista_Convidados", nullable = false)
    private boolean listaConvidados = false;

    @Column(name = "Fornecedores", nullable = false)
    private boolean fornecedores = false;
}
