package lotto;

import lotto.view.Input;
import lotto.view.Output;
import lotto.model.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static Cash cash;
    public static LottoCount lottoCount;
    public static LottoTrials lottoTrials = new LottoTrials();
    public static WinNumber winNumber;
    public static TotalResult totalResult = new TotalResult();

    public static void main(String[] args) throws IOException {
        inputCash();
        createLotto();
        inputWinningLotto();
        processLotto();
        Output.totalResultOutput(totalResult);
    }

    private static void inputCash() throws IOException {
        Output.startCashOutput();

        cash = new Cash(Input.startCashInput());

        lottoCount = new LottoCount(cash);

        Output.lottoCountOutput(lottoCount);
    }

    private static void createLotto() {
        for (int i = 0; i < lottoCount.getCount(); i++){
            LottoTrial lottoTrial = new LottoTrialRandom(new LottoPickerRandom());

            Output.lottoTrialOutput(lottoTrial);

            lottoTrials.add(lottoTrial);
        }
    }

    private static void inputWinningLotto() throws IOException {
        Output.winNumOutput();

        List<LottoBallNumber> winNum = Arrays.stream(Input.winNumInput())
                .map(v -> new LottoBallNumber(Integer.parseInt(v.trim())))
                .collect(Collectors.toList());

        Output.bonusNumOutput();

        winNumber = new WinNumber(new LottoTrialManual(winNum), new LottoBallNumber(Input.bonusNumInput()));

        System.out.println();
    }

    private static void processLotto() {
        for (int i = 0; i < lottoCount.getCount(); i++){
            totalResult.addResult(winNumber.compareLotto(lottoTrials.get(i)));
        }
    }
}
