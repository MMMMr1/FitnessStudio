package it.academy.fitness_studio.web;

import it.academy.fitness_studio.core.dto.error.ExceptionErrorDTO;
import it.academy.fitness_studio.core.dto.error.ExceptionStructuredDTO;
import it.academy.fitness_studio.core.dto.error.ExceptionListDTO;
import it.academy.fitness_studio.core.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionGlobal {
    //400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionListDTO> onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        List<ExceptionStructuredDTO> error = e.getBindingResult().getFieldErrors().stream()
                .map(s -> new ExceptionStructuredDTO(s.getField(), s.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionListDTO(error));
    }
    @ExceptionHandler(value = {ValidationRecipeException.class})
    public ResponseEntity<ExceptionListDTO> ArgumentRecipeNotValidException(
            ValidationRecipeException e) {
        List<ExceptionStructuredDTO> collect = Arrays.stream(e.getSuppressed())
                .map(s -> new ExceptionStructuredDTO(s.getMessage(),s.getCause().getMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionListDTO(collect));
    }
//    404
    @ExceptionHandler(value = {ProductNotFoundException.class,
            RecipeNotFoundException.class})
    public ResponseEntity<List<ExceptionErrorDTO>>  ArgumentUserNotFoundException(
            RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }

    //    409
    @ExceptionHandler(value = {ProductAlreadyExistException.class})
    public ResponseEntity<List<ExceptionErrorDTO>> ArgumentProductAlreadyExistException(
            RuntimeException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }
    @ExceptionHandler(value = {RecipeAlreadyExistException.class})
    public ResponseEntity<List<ExceptionErrorDTO>> ArgumentRecipeAlreadyExistException(
            RuntimeException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }
    @ExceptionHandler(value = {InvalidVersionException.class})
    public ResponseEntity<List<ExceptionErrorDTO>> ArgumentInvalidVersionException(
            RuntimeException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }
//    500
    @ExceptionHandler
    public ResponseEntity<List<ExceptionErrorDTO>> handler(Throwable e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }
}
