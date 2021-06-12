package by.resliv.resource;


import by.resliv.service.exception.KeyResponseExistException;
import by.resliv.service.exception.KeyResponseNotExistException;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(KeyResponseNotExistException.class)
    protected ResponseEntity<String> notFoundException(KeyResponseNotExistException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(KeyResponseExistException.class)
    protected ResponseEntity<String> alreadyExistException (KeyResponseExistException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,@NonNull HttpHeaders headers,@NonNull HttpStatus status,@NonNull WebRequest request) {
        Map<String, String> mapErr = new HashMap<>();
        for (FieldError fieldError : ex.getFieldErrors()) {
            mapErr.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(mapErr, HttpStatus.BAD_REQUEST);
    }
}
