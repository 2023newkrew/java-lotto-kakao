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
    private static WinNumber winNumber;
    private static final TotalResult totalResult = new TotalResult();

    public static void inputCash() throws IOException {
        Output.startCashOutput();

        Cash cash = new Cash(Input.startCashInput());

        lottoCount = new LottoCount(cash);

        Output.lottoCountOutput(lottoCount);
    }

    public static void createLotto() {
        for (int i = 0; i < lottoCount.getCount(); i++){
            LottoTrial lottoTrial = new LottoTrialRandom(new LottoPickerRandom());

            Output.lottoTrialOutput(lottoTrial);

            lottoTrials.add(lottoTrial);
        }
    }

    public static void inputWinningLotto() throws IOException {
        Output.winNumOutput();

        List<LottoBallNumber> winNum = Arrays.stream(Input.winNumInput())
                .map(v -> new LottoBallNumber(Integer.parseInt(v.trim())))
                .collect(Collectors.toList());

        Output.bonusNumOutput();

        winNumber = new WinNumber(new LottoTrialManual(winNum), new LottoBallNumber(Input.bonusNumInput()));

        System.out.println();
    }

    public static void processLotto() {
        for (int i = 0; i < lottoCount.getCount(); i++){
            totalResult.addResult(winNumber.compareLotto(lottoTrials.get(i)));
        }
    }

    public static void outputResult() {
        Output.totalResultOutput(totalResult);
    }
}
