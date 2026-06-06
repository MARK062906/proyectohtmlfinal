package com.denkitronik.discretasservice.services;

import com.denkitronik.discretasservice.entities.Modulo;
import com.denkitronik.discretasservice.entities.Tema;
import com.denkitronik.discretasservice.exceptions.RecursoNoEncontradoException;
import com.denkitronik.discretasservice.repositories.IModuloDao;
import com.denkitronik.discretasservice.repositories.ITemaDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ModuloServiceImpl implements IModuloService {

    private final IModuloDao moduloDao;
    private final ITemaDao temaDao;

    public ModuloServiceImpl(IModuloDao moduloDao, ITemaDao temaDao) {
        this.moduloDao = moduloDao;
        this.temaDao   = temaDao;
        System.out.println(">>> [OK] ModuloServiceImpl creado, moduloDao: " + moduloDao + ", temaDao: " + temaDao);
    }

    // ── Módulos ───────────────────────────────────────────────────

    @Override
    @Transactional(readOnly = true)
    public List<Modulo> findAll() {
        return moduloDao.findAllByOrderByOrdenAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public Modulo findById(Long id) {
        return moduloDao.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Módulo", id));
    }

    /** Devuelve el módulo con sus temas ya cargados (para la página de cada módulo) */
    @Override
    @Transactional(readOnly = true)
    public Modulo findByIdWithTemas(Long id) {
        return moduloDao.findByIdWithTemas(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Módulo", id));
    }

    @Override
    @Transactional
    public Modulo save(Modulo modulo) {
        return moduloDao.save(modulo);
    }

    @Override
    @Transactional
    public Modulo update(Long id, Modulo datos) {
        Modulo existente = findById(id);
        existente.setTitulo(datos.getTitulo());
        existente.setDescripcion(datos.getDescripcion());
        existente.setOrden(datos.getOrden());
        return moduloDao.save(existente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        findById(id);
        moduloDao.deleteById(id);
    }

    // ── Temas ─────────────────────────────────────────────────────

    /** Devuelve los temas de un módulo ordenados — para el bloqueo secuencial del Angular */
    @Override
    @Transactional(readOnly = true)
    public List<Tema> findTemasByModuloId(Long moduloId) {
        findById(moduloId); // lanza 404 si el módulo no existe
        return temaDao.findByModuloIdOrderByOrdenAsc(moduloId);
    }
}
