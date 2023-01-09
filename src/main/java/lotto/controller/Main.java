package lotto.controller;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Controller.inputCash();
        Controller.inputLottoManual();
        Controller.createLottoAuto();
        Controller.inputWinningLotto();
        Controller.processLotto();
        Controller.showResult();
    }
}
