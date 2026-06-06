package com.denkitronik.discretasservice.exceptions;

public class RecursoNoEncontradoException extends RuntimeException {

    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }

    public RecursoNoEncontradoException(String entidad, Long id) {
        super(entidad + " con id " + id + " no fue encontrado");
    }
}
