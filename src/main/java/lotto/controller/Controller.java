package lotto.controller;

import lotto.model.*;
import lotto.view.Input;
import lotto.view.Output;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    private static LottoCount lottoCount;
    private static final LottoTrials lottoTrials = new LottoTrials();
    private static WinLotto winLotto;
    private static final TotalResult totalResult = new TotalResult();

    public static void inputCash() throws IOException {
        Output.printEnterCash();

        Cash cash = new Cash(Input.startCashInput());

        lottoCount = new LottoCount(cash);

        Output.printLottoCount(lottoCount);
    }

    public static void createLotto() {
        lottoTrials.createLottoTrials(lottoCount);

        Output.printLottoTrials(lottoTrials);
    }

    public static void inputWinningLotto() throws IOException {
        Output.printEnterWinNum();

        List<LottoBall> winNum = Arrays.stream(Input.winNumInput())
                .map(v -> new LottoBall(Integer.parseInt(v.trim())))
                .collect(Collectors.toList());

        Output.printEnterBonusNum();

        winLotto = new WinLotto(new LottoManual(winNum), new LottoBall(Input.bonusNumInput()));

        System.out.println();
    }

    public static void processLotto() {
        for (int i = 0; i < lottoCount.getCount(); i++){
            totalResult.addResult(winLotto.compareLotto(lottoTrials.getLottoTrial(i)));
        }
    }

    public static void outputResult() {
        Output.printTotalResult(totalResult);
    }
}
