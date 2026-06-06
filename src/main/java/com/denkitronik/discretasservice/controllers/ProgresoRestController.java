package com.denkitronik.discretasservice.controllers;

import com.denkitronik.discretasservice.entities.Progreso;
import com.denkitronik.discretasservice.services.IProgresoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints de Progreso — equivale al progreso.service.ts del frontend Angular.
 * Permite guardar y consultar el avance de cada usuario por módulo.
 * Base: /api/v1/discretas-service/progresos
 */
@RestController
@RequestMapping("${api.version}/discretas-service/progresos")
public class ProgresoRestController {

    private final IProgresoService progresoService;

    public ProgresoRestController(IProgresoService progresoService) {
        this.progresoService = progresoService;
    }

    /** GET /api/v1/discretas-service/progresos/usuario/{usuarioId} */
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Progreso>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(progresoService.findByUsuarioId(usuarioId));
    }

    /** GET /api/v1/discretas-service/progresos/usuario/{usuarioId}/modulo/{moduloId} */
    @GetMapping("/usuario/{usuarioId}/modulo/{moduloId}")
    public ResponseEntity<Progreso> ver(@PathVariable Long usuarioId,
                                        @PathVariable Long moduloId) {
        return ResponseEntity.ok(progresoService.findByUsuarioIdAndModuloId(usuarioId, moduloId));
    }

    /**
     * POST /api/v1/discretas-service/progresos
     * Body: { "usuarioId": 1, "moduloId": 2, "porcentaje": 75, "puntaje": 80 }
     * Crea o actualiza el progreso (upsert).
     */
    @PostMapping
    public ResponseEntity<Progreso> guardar(
            @RequestParam Long usuarioId,
            @RequestParam Long moduloId,
            @RequestParam Integer porcentaje,
            @RequestParam(required = false) Integer puntaje) {
        return ResponseEntity.ok(progresoService.saveOrUpdate(usuarioId, moduloId, porcentaje, puntaje));
    }
}
