package exception;

public class DuplicateNumberException extends IllegalArgumentException {

    public DuplicateNumberException() {
    }

    public DuplicateNumberException(String message) {
        super(message);
    }

}
