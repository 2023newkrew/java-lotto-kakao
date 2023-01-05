package lotto.exception;

public class ErrorMessageFormatter {


    private ErrorMessageFormatter() {
    }

    public static <T> String makeErrorMessage(String expected, T actual, String context) {
        return "EXPECTED: " + expected + '\n'
                + "ACTUAL: " + actual.toString() + '\n'
                + "CONTEXT: " + context + '\n';
    }


}
