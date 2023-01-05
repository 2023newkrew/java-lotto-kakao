package lotto.controller;

import lotto.domain.*;
import lotto.view.View;

import static lotto.domain.constants.LottoConstants.*;

public class Controller {
    private final View view = new View(System.in, System.out, System.err);
    private Cash cash;
    private LottoCount lottoCountRandom;
    private LottoCount lottoCountManual;
    private LottoTrials lottoTrials = new LottoTrials();
    private WinNumber winNumber;
    private TotalResult totalResult = new TotalResult();
    private void input(){
        Cash lottoCount = view.inputCash();
        lottoCountManual = view.inputLottoCountManual();
        lottoCountRandom = new LottoCount(lottoCount.minus(lottoCountManual.getCount()*LOTTO_PRICE));
    }
    private void inputManualLotto(){
        for (int i=1;i<=lottoCountManual.getCount();i++){
            LottoTrial lottoTrial = view.inputLottoManual();
            lottoTrials.add(lottoTrial);
        }
        view.printLottoCountWithManual(lottoCountManual, lottoCountRandom);
    }
    private void createRandomLotto(){
        for (int i=1;i<=lottoCountRandom.getCount();i++){
            LottoTrial lottoTrial = new LottoTrial.Builder()
                    .addRandomBalls()
                    .build();
            lottoTrials.add(lottoTrial);
        }
        view.printLottoTrials(lottoTrials);
    }

    private void inputWinningLotto() {
        winNumber = view.inputWinningLotto();
    }

    private void processLotto(){
        for (int i=0; i<lottoTrials.size();i++){
            totalResult.add(winNumber.compareLotto(lottoTrials.get(i)));
        }
        view.printTotalResult(totalResult);
    }

    public void run(){
        try {
            input();
            inputManualLotto();
            createRandomLotto();
            inputWinningLotto();
            processLotto();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
