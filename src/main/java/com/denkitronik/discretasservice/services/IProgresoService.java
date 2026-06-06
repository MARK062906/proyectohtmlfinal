package com.denkitronik.discretasservice.services;

import com.denkitronik.discretasservice.entities.Progreso;

import java.util.List;

public interface IProgresoService {
    List<Progreso> findByUsuarioId(Long usuarioId);
    Progreso findByUsuarioIdAndModuloId(Long usuarioId, Long moduloId);
    Progreso saveOrUpdate(Long usuarioId, Long moduloId, Integer porcentaje, Integer puntaje);
}
