package by.katomakhin.app.exceptions;

public class UserOrPinIncorrectException extends RuntimeException {
    public UserOrPinIncorrectException(String message) {
        super(message);
    }
}
