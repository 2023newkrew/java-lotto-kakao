package lotto.view;

import lotto.domain.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static lotto.view.ViewConstant.Korean.*;

public class View {
    private final InputStream in;
    private final PrintStream out;
    private final PrintStream err;

    private final Scanner sc;
    public View(InputStream in, PrintStream out, PrintStream err) {
        this.in = in;
        this.out = out;
        this.err = err;
        sc = new Scanner(this.in);
    }

    public Cash inputCash(){
        out.printf(INPUT_CASH_PROMPT);
        return new Cash(Long.parseLong(sc.nextLine()));
    }

    public void printLottoCount(LottoCount lottoCount){
        out.printf(LOTTO_PURCHASE_PROMPT, lottoCount);
    }
    
    public void printLottoCountWithManual(LottoCount lottoManualCount, LottoCount lottoAutoCount){
        out.printf(LOTTO_MANUAL_AUTO_PURCHASE_PROMPT, lottoManualCount, lottoAutoCount);
    }
    public void printLottoTrials(LottoTrials lottoTrials){
        while(lottoTrials.hasNext()){
            out.println(lottoTrials.next());
        }
    }

    public WinNumber inputWinningLotto() {
        List<LottoBallNumber> winNum = inputLottoBallNumberManual(INPUT_WIN_LOTTO_PROMPT);
        out.printf(INPUT_BONUS_BALL_PROMPT);
        return new WinNumber(new LottoTrialManual(winNum),
                LottoBallNumber.get(Integer.parseInt(sc.nextLine())));
    }

    public void printTotalResult(TotalResult totalResult){
        out.println(totalResult);
    }

    public LottoCount inputLottoCountManual() {
        out.printf(INPUT_MANUAL_PROMPT);
        return new LottoCount(Integer.parseInt(sc.nextLine()));
    }

    public LottoTrial inputLottoManual() {
        return new LottoTrialManual(inputLottoBallNumberManual(INPUT_MANUAL_LOTTO_PROMPT));
    }
    private List<LottoBallNumber> inputLottoBallNumberManual(String promptString){
        out.printf(promptString);
        return Arrays.stream(sc.nextLine().split(","))
                .map(v -> LottoBallNumber.get(Integer.parseInt(v.trim())))
                .collect(Collectors.toList());
    }
}
