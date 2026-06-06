package com.denkitronik.discretasservice.repositories;

import com.denkitronik.discretasservice.entities.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITemaDao extends JpaRepository<Tema, Long> {

    /** Temas de un módulo ordenados — para el bloqueo secuencial del Angular */
    List<Tema> findByModuloIdOrderByOrdenAsc(Long moduloId);
}
