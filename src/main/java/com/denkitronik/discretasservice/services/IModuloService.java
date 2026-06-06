package com.denkitronik.discretasservice.services;

import com.denkitronik.discretasservice.entities.Modulo;
import com.denkitronik.discretasservice.entities.Tema;

import java.util.List;

public interface IModuloService {

    // ── Módulos ───────────────────────────────────────────────────
    List<Modulo> findAll();
    Modulo findById(Long id);
    Modulo findByIdWithTemas(Long id);
    Modulo save(Modulo modulo);
    Modulo update(Long id, Modulo modulo);
    void delete(Long id);

    // ── Temas por módulo ──────────────────────────────────────────
    List<Tema> findTemasByModuloId(Long moduloId);
}
