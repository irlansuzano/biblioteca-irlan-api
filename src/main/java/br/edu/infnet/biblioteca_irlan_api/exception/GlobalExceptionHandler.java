package br.edu.infnet.biblioteca_irlan_api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Void> tratarRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> tratarArgumentoInvalido(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().build();
    }
}
