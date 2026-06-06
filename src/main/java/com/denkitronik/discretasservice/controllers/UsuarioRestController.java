package com.denkitronik.discretasservice.controllers;

import com.denkitronik.discretasservice.entities.Usuario;
import com.denkitronik.discretasservice.services.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoints de Usuario — equivale al perfil y login del frontend Angular.
 * Base: /api/v1/discretas-service/usuarios
 */
@RestController
@RequestMapping("${api.version}/discretas-service/usuarios")
public class UsuarioRestController {

    private final IUsuarioService usuarioService;

    public UsuarioRestController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /** GET /api/v1/discretas-service/usuarios?page=0&size=10 */
    @GetMapping
    public ResponseEntity<Page<Usuario>> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(usuarioService.findAll(pageable));
    }

    /** GET /api/v1/discretas-service/usuarios/{id} */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> ver(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    /** GET /api/v1/discretas-service/usuarios/email/{email} */
    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> verPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(usuarioService.findByEmail(email));
    }

    /** POST /api/v1/discretas-service/usuarios  → registro */
    @PostMapping
    public ResponseEntity<Usuario> crear(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    /** PUT /api/v1/discretas-service/usuarios/{id}  → editar perfil */
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id,
                                              @Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.update(id, usuario));
    }

    /** DELETE /api/v1/discretas-service/usuarios/{id} */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
