package lotto.utils;

public class ErrorMessageFormatter {
    private ErrorMessageFormatter() {
    }
    public static <T> String makeErrorMessage(String expected, T actual, String context) {
        return "\nEXPECTED: " + expected + '\n'
                + "ACTUAL: " + actual.toString() + '\n'
                + "CONTEXT: " + context + '\n';
    }

}
