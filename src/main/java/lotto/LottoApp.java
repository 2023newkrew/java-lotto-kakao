package lotto;

import lotto.controller.LottoController;

public class LottoApp {

    private AppConfig appConfig;

    public LottoApp(AppConfig appConfig){
        this.appConfig = appConfig;

    }

    public void run(){
        new LottoController(appConfig).run();
    }
}
