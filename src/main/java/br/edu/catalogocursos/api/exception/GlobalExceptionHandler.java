package br.edu.catalogocursos.api.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarValidacao(
            MethodArgumentNotValidException exception,
            HttpServletRequest request) {

        String mensagem = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .findFirst()
                .orElse("Dados inválidos.");

        return criarResposta(HttpStatus.BAD_REQUEST, mensagem, request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> tratarJsonInvalido(
            HttpMessageNotReadableException exception,
            HttpServletRequest request) {

        return criarResposta(
                HttpStatus.BAD_REQUEST,
                "JSON inválido.",
                request
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> tratarNaoEncontrado(
            EntityNotFoundException exception,
            HttpServletRequest request) {

        return criarResposta(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                request
        );
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, Object>> tratarConflito(
            IllegalStateException exception,
            HttpServletRequest request) {

        return criarResposta(
                HttpStatus.CONFLICT,
                exception.getMessage(),
                request
        );
    }

    private ResponseEntity<Map<String, Object>> criarResposta(
            HttpStatus status,
            String mensagem,
            HttpServletRequest request) {

        Map<String, Object> resposta = Map.of(
                "status", status.value(),
                "message", mensagem,
                "path", request.getRequestURI()
        );

        return ResponseEntity.status(status).body(resposta);
    }
}
