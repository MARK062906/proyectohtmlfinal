package com.denkitronik.discretasservice.controllers;

import com.denkitronik.discretasservice.entities.Modulo;
import com.denkitronik.discretasservice.entities.Tema;
import com.denkitronik.discretasservice.services.IModuloService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints consumidos por el Angular:
 *
 *  GET  /v1/discretas-service/modulos
 *       → dashboard.ts: carga las 3 tarjetas del dashboard
 *
 *  GET  /v1/discretas-service/modulos/{id}
 *       → modulo1/2/3.ts: carga el título y descripción del módulo
 *
 *  GET  /v1/discretas-service/modulos/{id}/temas
 *       → modulo1/2/3.ts: carga la lista de temas (reemplaza el arreglo local)
 */
@RestController
@RequestMapping("/v1/discretas-service/modulos")
public class ModuloRestController {

    private final IModuloService moduloService;


    public ModuloRestController(IModuloService moduloService) {

        this.moduloService = moduloService;
        System.out.println(">>> [OK] ModuloRestController creado, servicio: " + moduloService);
    }

    /**
     * GET /v1/discretas-service/modulos
     * Devuelve los 3 módulos ordenados para el dashboard.
     * Respuesta: [{id, titulo, descripcion, orden}, ...]
     */
    @GetMapping
    public ResponseEntity<List<Modulo>> listar() {
        return ResponseEntity.ok(moduloService.findAll());
    }

    /**
     * GET /v1/discretas-service/modulos/{id}
     * Devuelve un módulo con sus temas incluidos.
     * Usado por modulo1.ts, modulo2.ts, modulo3.ts al cargar la página.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Modulo> ver(@PathVariable Long id) {
        return ResponseEntity.ok(moduloService.findByIdWithTemas(id));
    }

    /**
     * GET /v1/discretas-service/modulos/{id}/temas
     * Devuelve solo los temas de un módulo ordenados.
     * Respuesta: [{id, titulo, texto, orden}, ...]
     * Reemplaza el arreglo CONTENIDOS del contenido.ts del Angular.
     */
    @GetMapping("/{id}/temas")
    public ResponseEntity<List<Tema>> listarTemas(@PathVariable Long id) {
        return ResponseEntity.ok(moduloService.findTemasByModuloId(id));
    }

    /**
     * POST /v1/discretas-service/modulos
     * Crea un módulo nuevo.
     */
    @PostMapping
    public ResponseEntity<Modulo> crear(@Valid @RequestBody Modulo modulo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(moduloService.save(modulo));
    }

    /**
     * PUT /v1/discretas-service/modulos/{id}
     * Actualiza título, descripción u orden de un módulo.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Modulo> actualizar(@PathVariable Long id,
                                             @Valid @RequestBody Modulo modulo) {
        return ResponseEntity.ok(moduloService.update(id, modulo));
    }

    /**
     * DELETE /v1/discretas-service/modulos/{id}
     * Elimina un módulo y sus temas en cascada.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        moduloService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
