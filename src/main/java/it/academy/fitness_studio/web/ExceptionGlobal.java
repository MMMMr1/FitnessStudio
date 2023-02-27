package it.academy.fitness_studio.web;

import it.academy.fitness_studio.core.dto.exception.Exception400DTO;
import it.academy.fitness_studio.core.dto.exception.ExceptionStructuredDTO;
import it.academy.fitness_studio.core.dto.exception.ExceptionListDTO;
import it.academy.fitness_studio.core.exception.ValidationProductException;
import it.academy.fitness_studio.core.exception.ValidationRecipeException;
import it.academy.fitness_studio.core.exception.ValidationUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionGlobal {
//    @ExceptionHandler(value = {NullPointerException.class,
//            ArrayIndexOutOfBoundsException.class})
//    public ResponseEntity<Exception400DTO> handlerNPE(RuntimeException e ){
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(new Exception400DTO(e.getLocalizedMessage()));
//    }
    @ExceptionHandler(value = {ValidationUserException.class})
    public ResponseEntity<Exception400DTO> ArgumentUserNotValidException(
            ValidationUserException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Exception400DTO(e.getMessage()));
    }
    @ExceptionHandler(value = {ValidationProductException.class})
    public ResponseEntity<Exception400DTO> ArgumentProductNotValidException(
            ValidationProductException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Exception400DTO(e.getMessage()));
    }
    @ExceptionHandler(value = {ValidationRecipeException.class})
    public ResponseEntity<Exception400DTO> ArgumentRecipeNotValidException(
            ValidationRecipeException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Exception400DTO(e.getMessage()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionListDTO> onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        List<ExceptionStructuredDTO> error = e.getBindingResult().getFieldErrors().stream()
                    .map(s -> new ExceptionStructuredDTO(s.getField(), s.getDefaultMessage()))
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest()
                    .body(new ExceptionListDTO(error));
    }
    @ExceptionHandler
    public ResponseEntity<ExceptionStructuredDTO> handlerNPE(Throwable e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionStructuredDTO("error", e.getMessage()));
    }
//    --------------------------------------------------------------------
}
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<ExceptionListDTO> onMethodArgumentNotValidException(
//            IllegalArgumentException e
//    ) {
//        Throwable[] suppressed = e.getSuppressed();
//
//        return ResponseEntity.badRequest().body(new ExceptionListDTO("structured_error",e.));
//    }
//}



//    @ResponseBody
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<ExceptionListDTO> onConstraintValidationException(
//            ConstraintViolationException e
//    ) {
//        List<ExceptionDTO> error = e.getConstraintViolations().stream()
//                .map(s -> new ExceptionDTO(s.getPropertyPath().toString(),
//                        s.getMessage()))
//                .collect(Collectors.toList());
//        return ResponseEntity.badRequest().body(new ExceptionListDTO(error));
//    }

//        final List<Violation> violations = e.getConstraintViolations().stream()
//                .map(
//                        violation -> new Violation(
//                                violation.getPropertyPath().toString(),
//                                violation.getMessage()
//                        )
//                )
//                .collect(Collectors.toList());
//        return   ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body( new ValidationErrorResponse(violations));
//    }