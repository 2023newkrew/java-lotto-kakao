package lotto.views;

public interface Console {
    void printOutput(String message);

    void printError(String message);

    String input();
}