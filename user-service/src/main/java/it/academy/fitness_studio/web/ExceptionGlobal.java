package it.academy.fitness_studio.web;

import it.academy.fitness_studio.core.dto.error.ExceptionErrorDTO;
import it.academy.fitness_studio.core.dto.error.ExceptionStructuredDTO;
import it.academy.fitness_studio.core.dto.error.ExceptionListDTO;
import it.academy.fitness_studio.core.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionGlobal {

    //400 @Validated
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionListDTO> onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        List<ExceptionStructuredDTO> error = e.getBindingResult().getFieldErrors().stream()
                .map(s -> new ExceptionStructuredDTO(s.getField(), s.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionListDTO(error));
    }
//    400
    @ExceptionHandler(value = {NumberFormatException.class})
    public ResponseEntity<List<ExceptionErrorDTO>>  ArgumentUserNotValidException(
            ValidationUserException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }



    @ExceptionHandler(value = {UserAlreadyExistException.class })
    public ResponseEntity<List<ExceptionErrorDTO>> ArgumentUserAlreadyExistException(
            RuntimeException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }    @ExceptionHandler(value = {InvalidVersionException.class})
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