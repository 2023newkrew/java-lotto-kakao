package lotto;

import lotto.controller.LottoSimulator;
import lotto.model.RandomLottosGenerator;

public class Application {

    public static void main(String[] args) {
        new LottoSimulator(new RandomLottosGenerator()).run();
    }
}
