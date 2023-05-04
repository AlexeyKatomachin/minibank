package by.katomakhin.app.controller.handler;

import by.katomakhin.app.exceptions.AccountDoesNotBelongUserException;
import by.katomakhin.app.exceptions.AccountDoesNotExistException;
import by.katomakhin.app.exceptions.UserOrPinIncorrectException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class AccountControllerAdviceHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({AccountDoesNotBelongUserException.class})
    public ResponseEntity<Object> accountDoesNotBelongUserException(AccountDoesNotBelongUserException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AccountDoesNotExistException.class})
    public ResponseEntity<Object> accountDoesNotExistException(AccountDoesNotExistException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserOrPinIncorrectException.class})
    public ResponseEntity<Object> userOrPinIncorrectException(UserOrPinIncorrectException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
