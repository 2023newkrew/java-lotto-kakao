package lotto.views;

import java.util.Scanner;

public class StandardConsole implements Console {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void printOutput(String message) {
        System.out.println(message);
    }

    @Override
    public void printError(String message) {
        System.out.println("[ERROR] " + message);
    }

    @Override
    public String input() {
        String s = scanner.nextLine();
        return s;
    }
}