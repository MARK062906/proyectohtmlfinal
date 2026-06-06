package com.denkitronik.discretasservice.services;

import com.denkitronik.discretasservice.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUsuarioService {
    Page<Usuario> findAll(Pageable pageable);
    Usuario findById(Long id);
    Usuario findByEmail(String email);
    Usuario save(Usuario usuario);
    Usuario update(Long id, Usuario usuario);
    void delete(Long id);
}
