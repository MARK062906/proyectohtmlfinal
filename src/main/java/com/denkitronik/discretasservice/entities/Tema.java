package com.denkitronik.discretasservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

/**
 * Equivale a ContenidoItem del Angular:
 *   id     → ContenidoItem.id
 *   titulo → ContenidoItem.titulo
 *   texto  → ContenidoItem.texto
 *   orden  → posición dentro del módulo (para el bloqueo secuencial)
 */
@Entity
@Table(name = "temas")
@Getter @Setter
public class Tema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título no puede estar vacío")
    @Column(nullable = false, length = 150)
    private String titulo;

    @NotBlank(message = "El texto no puede estar vacío")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String texto;        // mismo nombre que en el Angular

    @NotNull(message = "El orden es obligatorio")
    @Positive(message = "El orden debe ser mayor a 0")
    @Column(nullable = false)
    private Integer orden;       // define el orden de bloqueo secuencial

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modulo_id", nullable = false)
    @JsonBackReference
    private Modulo modulo;
}
