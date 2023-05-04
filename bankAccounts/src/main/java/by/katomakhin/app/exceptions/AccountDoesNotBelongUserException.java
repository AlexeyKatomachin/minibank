package by.katomakhin.app.exceptions;

public class AccountDoesNotBelongUserException extends RuntimeException {
    public AccountDoesNotBelongUserException(String message) {
        super(message);
    }
}
