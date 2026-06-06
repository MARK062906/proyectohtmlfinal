package com.denkitronik.discretasservice.exceptions;

/** Se lanza cuando se intenta crear un recurso que viola una restricción de unicidad. */
public class RecursoYaExisteException extends RuntimeException {

    public RecursoYaExisteException(String mensaje) {
        super(mensaje);
    }
}
