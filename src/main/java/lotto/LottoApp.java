package lotto;

import lotto.controller.LottoController;

public class LottoApp {
    public static void main(String[] args) {
        LottoController lottoController = LottoController.getInstance();
        lottoController.run();
    }
}
