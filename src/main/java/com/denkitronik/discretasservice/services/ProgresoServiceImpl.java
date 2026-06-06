package com.denkitronik.discretasservice.services;

import com.denkitronik.discretasservice.entities.Modulo;
import com.denkitronik.discretasservice.entities.Progreso;
import com.denkitronik.discretasservice.entities.Usuario;
import com.denkitronik.discretasservice.exceptions.RecursoNoEncontradoException;
import com.denkitronik.discretasservice.repositories.IModuloDao;
import com.denkitronik.discretasservice.repositories.IProgresoDao;
import com.denkitronik.discretasservice.repositories.IUsuarioDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProgresoServiceImpl implements IProgresoService {

    private final IProgresoDao progresoDao;
    private final IUsuarioDao usuarioDao;
    private final IModuloDao moduloDao;

    public ProgresoServiceImpl(IProgresoDao progresoDao,
                               IUsuarioDao usuarioDao,
                               IModuloDao moduloDao) {
        this.progresoDao = progresoDao;
        this.usuarioDao  = usuarioDao;
        this.moduloDao   = moduloDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Progreso> findByUsuarioId(Long usuarioId) {
        return progresoDao.findByUsuarioId(usuarioId);
    }

    @Override
    @Transactional(readOnly = true)
    public Progreso findByUsuarioIdAndModuloId(Long usuarioId, Long moduloId) {
        return progresoDao.findByUsuarioIdAndModuloId(usuarioId, moduloId)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Progreso del usuario " + usuarioId + " en módulo " + moduloId + " no encontrado"));
    }

    @Override
    @Transactional
    public Progreso saveOrUpdate(Long usuarioId, Long moduloId, Integer porcentaje, Integer puntaje) {
        Usuario usuario = usuarioDao.findById(usuarioId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario", usuarioId));
        Modulo modulo = moduloDao.findById(moduloId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Módulo", moduloId));

        Progreso progreso = progresoDao.findByUsuarioIdAndModuloId(usuarioId, moduloId)
                .orElseGet(Progreso::new);

        progreso.setUsuario(usuario);
        progreso.setModulo(modulo);
        progreso.setPorcentajeCompletado(porcentaje);
        progreso.setCompletado(porcentaje >= 100);
        if (puntaje != null) progreso.setUltimoPuntaje(puntaje);

        return progresoDao.save(progreso);
    }
}
