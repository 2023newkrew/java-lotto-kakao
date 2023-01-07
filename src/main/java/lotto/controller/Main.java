package lotto.controller;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Controller.inputCash();
        Controller.createLotto();
        Controller.inputWinningLotto();
        Controller.processLotto();
        Controller.showResult();
    }
}
