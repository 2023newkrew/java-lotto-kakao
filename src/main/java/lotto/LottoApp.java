package lotto;

import lotto.config.AppConfig;
import lotto.controller.LottoController;

public class LottoApp {


    public void run() {
        LottoController.createLottoController(AppConfig.getInstance().getView()).play();
    }

    public static void main(String[] args) {
        new LottoApp().run();



    }
}
