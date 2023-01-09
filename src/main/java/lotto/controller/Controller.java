package lotto.controller;

import lotto.model.*;
import lotto.view.Input;
import lotto.view.Output;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    private static Cash buyCash;
    private static LottoCount lottoCountManual;
    private static LottoCount lottoCountAuto;
    private static final LottoTrials lottoTrials = new LottoTrials();
    private static WinLotto winLotto;
    private static final TotalResult totalResult = new TotalResult();

    public static void inputCash() throws IOException {
        Output.printEnterCash();

        buyCash = new BuyCash(Input.cashInput());
    }

    public static void inputLottoManual() throws IOException {
        Output.printEnterManualNum();

        lottoCountManual = new LottoCount(Input.manualNumInput());
        lottoCountAuto = new LottoCount(new LottoCount(buyCash).getCount() - lottoCountManual.getCount());

        if (lottoCountManual.getCount() == 0) return;

        Output.printEnterLottoManual();

        for (int i = 0; i < lottoCountManual.getCount(); i++) {
            List<LottoBall> manualLotto = Arrays.stream(Input.lottoManualInput())
                    .map(v -> new LottoBall(Integer.parseInt(v.trim())))
                    .collect(Collectors.toList());

            lottoTrials.addLottoManualTrial(new Lotto(manualLotto));
        }
    }

    public static void createLottoAuto() {
        Output.printLottoCount(lottoCountManual, lottoCountAuto);

        lottoTrials.createLottoAutoTrials(lottoCountAuto);

        Output.printLottoTrials(lottoTrials);
    }

    public static void inputWinningLotto() throws IOException {
        Output.printEnterWinNum();

        List<LottoBall> winNum = Arrays.stream(Input.winNumInput())
                .map(v -> new LottoBall(Integer.parseInt(v.trim())))
                .collect(Collectors.toList());

        Output.printEnterBonusNum();

        winLotto = new WinLotto(new Lotto(winNum), new LottoBall(Input.bonusNumInput()));

        System.out.println();
    }

    public static void processLotto() {
        for (int i = 0; i < lottoTrials.getSize(); i++){
            totalResult.addResult(winLotto.compareLotto(lottoTrials.getLottoTrial(i)));
        }
    }

    public static void showResult() {
        Output.printTotalResult(totalResult);
    }
}
