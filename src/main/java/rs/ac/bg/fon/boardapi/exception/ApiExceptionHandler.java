package rs.ac.bg.fon.boardapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import rs.ac.bg.fon.boardapi.exception.custom.BoardNotFoundException;
import rs.ac.bg.fon.boardapi.exception.custom.EmployeeNotFoundException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {EmployeeNotFoundException.class, BoardNotFoundException.class})
    public ResponseEntity<CustomException> onNotFoundException(RuntimeException ex) {
        CustomException exception = new CustomException(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse onMethodArgumentNotValidExceptionI(MethodArgumentNotValidException ex) {
        Set<Violation> violations = new HashSet<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            Violation violation = new Violation(
                    fieldError.getField(),
                    fieldError.getDefaultMessage(),
                    ZonedDateTime.now(ZoneId.of("Z"))
            );
            violations.add(violation);
        }

        for(ObjectError error:ex.getBindingResult().getGlobalErrors()){
            Violation violation = new Violation(
                    error.getCode(),
                    error.getDefaultMessage(),
                    ZonedDateTime.now(ZoneId.of("Z"))
            );
            violations.add(violation);
        }

        return new ErrorResponse(violations);
    }
}

record CustomException(String message, HttpStatus http, ZonedDateTime timestamp) {
}

record Violation(String field, String message, ZonedDateTime timestamp) {
}

record ErrorResponse(Set<Violation> violations) {
}