package com.denkitronik.discretasservice.repositories;

import com.denkitronik.discretasservice.entities.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IModuloDao extends JpaRepository<Modulo, Long> {

    /** Lista los 3 módulos ordenados — alimenta el dashboard */
    List<Modulo> findAllByOrderByOrdenAsc();

    /** Módulo con sus temas incluidos en una sola query — evita N+1 */
    @Query("SELECT m FROM Modulo m LEFT JOIN FETCH m.temas t WHERE m.id = :id ORDER BY t.orden ASC")
    Optional<Modulo> findByIdWithTemas(Long id);
}
