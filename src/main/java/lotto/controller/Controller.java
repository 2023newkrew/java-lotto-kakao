package lotto.controller;

import lotto.domain.*;
import lotto.view.View;

public class Controller {
    private final View view = new View(System.in, System.out, System.err);

    public void run(){
        try {
            LottoTrials trials = inputTrials();
            view.printLottoTrials(trials);
            WinNumber winNumber = inputWinningLotto();
            view.printTotalResult(getTotalResult(winNumber, trials));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    private LottoTrials inputTrials(){
        LottoTrials trials = new LottoTrials();
        Cash cash = getCash();
        LottoCount lottoCountTotal = new LottoCount(cash);
        LottoCount lottoCountManual = getLottoCountManual();
        LottoCount lottoCountRandom = new LottoCount(lottoCountTotal.minus(lottoCountManual));
        trials.addAll(inputManualLotto(lottoCountManual));
        view.printLottoCountWithManual(lottoCountManual, lottoCountRandom);
        trials.addAll(createRandomLotto(lottoCountRandom));
        return trials;
    }

    private Cash getCash(){
        return view.inputCash();
    }
    private LottoCount getLottoCountManual(){
        return view.inputLottoCountManual();
    }
    private LottoTrials inputManualLotto(LottoCount lottoCountManual){
        LottoTrials result = new LottoTrials();
        for (int i=1;i<=lottoCountManual.getCount();i++){
            LottoTrial lottoTrial = view.inputLottoManual();
            result.add(lottoTrial);
        }
        return result;
    }
    private LottoTrials createRandomLotto(LottoCount lottoCountRandom){
        LottoTrials result = new LottoTrials();
        for (int i=1;i<=lottoCountRandom.getCount();i++){
            LottoTrial lottoTrial = new LottoTrial.Builder()
                    .addRandomBalls()
                    .build();
            result.add(lottoTrial);
        }
        return result;
    }



    private WinNumber inputWinningLotto() {
        return view.inputWinningLotto();
    }

    private TotalResult getTotalResult(WinNumber winNumber, LottoTrials lottoTrials){
        TotalResult totalResult = new TotalResult();
        for (int i=0; i<lottoTrials.size();i++){
            totalResult.add(winNumber.compareLotto(lottoTrials.get(i)));
        }
        return totalResult;
    }
}
