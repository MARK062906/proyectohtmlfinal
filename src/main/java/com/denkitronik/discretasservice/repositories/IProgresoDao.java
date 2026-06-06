package com.denkitronik.discretasservice.repositories;

import com.denkitronik.discretasservice.entities.Progreso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IProgresoDao extends JpaRepository<Progreso, Long> {

    List<Progreso> findByUsuarioId(Long usuarioId);

    Optional<Progreso> findByUsuarioIdAndModuloId(Long usuarioId, Long moduloId);
}
