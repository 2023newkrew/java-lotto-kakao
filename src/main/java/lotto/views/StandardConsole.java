package lotto.views;

import java.util.Scanner;

class StandardConsole implements Console {

    Scanner scanner = new Scanner(System.in);

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
        return scanner.nextLine();
    }
}