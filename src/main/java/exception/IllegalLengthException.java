package exception;

public class IllegalLengthException extends IllegalArgumentException {

    public IllegalLengthException() {}

    public IllegalLengthException(String message) {
        super(message);
    }

}
