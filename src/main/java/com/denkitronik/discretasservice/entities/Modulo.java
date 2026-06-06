package com.denkitronik.discretasservice.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uno de los 3 módulos de la plataforma.
 * Equivale a las páginas modulo1, modulo2, modulo3 del Angular.
 */
@Entity
@Table(name = "modulos")
@Getter @Setter
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título no puede estar vacío")
    @Column(nullable = false, length = 100)
    private String titulo;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @NotNull(message = "El orden es obligatorio")
    @Positive(message = "El orden debe ser mayor a 0")
    @Column(nullable = false)
    private Integer orden;   // 1, 2 o 3

    // Lista de temas pertenecientes a este módulo
    @OneToMany(mappedBy = "modulo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Tema> temas = new ArrayList<>();
}
