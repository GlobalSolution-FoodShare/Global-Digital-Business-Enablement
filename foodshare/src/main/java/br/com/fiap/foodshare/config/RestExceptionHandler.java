package br.com.fiap.foodshare.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.fiap.foodshare.models.RestValidationError;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<RestValidationError>> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException e) {
        List<RestValidationError> errors = new ArrayList<>();
        e.getFieldErrors().forEach(v -> errors.add(new RestValidationError(v.getField(), v.getDefaultMessage())));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<List<RestValidationError>> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex) {
        List<RestValidationError> errors = new ArrayList<>();

        // Extrair a mensagem de erro da exceção
        String errorMessage = ex.getMostSpecificCause().getMessage();
        errors.add(new RestValidationError("error", errorMessage));

        return ResponseEntity.badRequest().body(errors);
    }

}
