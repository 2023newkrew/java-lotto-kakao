package lotto;

import lotto.controller.LottoController;

public class Application {
    static LottoController lottoController = new LottoController();

    public static void main(String[] args) {
        lottoController.start();
    }
}
