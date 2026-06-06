package com.denkitronik.discretasservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Registra el progreso de un Usuario en un Módulo.
 * Equivalente al progreso.service.ts del frontend Angular.
 */
@Entity
@Table(
    name = "progresos",
    uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "modulo_id"})
)
@Getter @Setter
public class Progreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "progresos", "password"})
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modulo_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "temas"})
    private Modulo modulo;

    @Column(name = "porcentaje_completado", nullable = false)
    private Integer porcentajeCompletado = 0;   // 0–100

    @Column(nullable = false)
    private Boolean completado = false;

    @Column(name = "ultimo_puntaje")
    private Integer ultimoPuntaje;              // Último resultado de evaluación (%)

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date();
    }
}
