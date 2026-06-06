package com.denkitronik.discretasservice.services;

import com.denkitronik.discretasservice.entities.Usuario;
import com.denkitronik.discretasservice.exceptions.RecursoNoEncontradoException;
import com.denkitronik.discretasservice.exceptions.RecursoYaExisteException;
import com.denkitronik.discretasservice.repositories.IUsuarioDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final IUsuarioDao usuarioDao;

    public UsuarioServiceImpl(IUsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Usuario> findAll(Pageable pageable) {
        return usuarioDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return usuarioDao.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario", id));
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByEmail(String email) {
        return usuarioDao.findByEmail(email)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario con email " + email + " no encontrado"));
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        if (usuarioDao.existsByEmail(usuario.getEmail())) {
            throw new RecursoYaExisteException("Ya existe un usuario con el email: " + usuario.getEmail());
        }
        return usuarioDao.save(usuario);
    }

    @Override
    @Transactional
    public Usuario update(Long id, Usuario datos) {
        Usuario existente = findById(id);

        // Solo actualizamos si el email cambia y no está tomado por otro usuario
        if (!existente.getEmail().equals(datos.getEmail()) && usuarioDao.existsByEmail(datos.getEmail())) {
            throw new RecursoYaExisteException("El email " + datos.getEmail() + " ya está en uso");
        }

        existente.setNombre(datos.getNombre());
        existente.setApellido(datos.getApellido());
        existente.setEmail(datos.getEmail());
        existente.setFotoUrl(datos.getFotoUrl());

        return usuarioDao.save(existente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        findById(id); // lanza 404 si no existe
        usuarioDao.deleteById(id);
    }
}
