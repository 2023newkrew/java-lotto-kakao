package controller;

import domain.*;
import view.InputView;
import view.OutputView;
import view.WinningStatistics;
import utils.LottoCalculator;
import utils.LottoGenerator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoController {

    private InputView inputView;
    private OutputView outputView;

    public LottoController() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void run() {
        Payment payment = new Payment(inputView.getUserInputPayment());
        outputView.printNumberOfLotto(LottoCalculator.calculateNumberOfLotto(payment));

        List<LottoNumbers> purchasedLotto = LottoGenerator.generateLotto(payment);
        outputView.printPurchasedLotto(purchasedLotto);

        LottoNumbers winLottoNumbers = getWinLottoNumbers();
        LottoNumber bonusBall = new LottoNumber(inputView.getUserInputBonusBallNumbers());

        handleLottoResult(execute(purchasedLotto, winLottoNumbers, bonusBall), payment);
    }

    private Map<Rank, Integer> execute(List<LottoNumbers> lottoNumbersList, LottoNumbers winLottoNumbers, LottoNumber bonusBall) {
        Lotto lotto = new Lotto(lottoNumbersList);

        return lotto.rankEachLotto(winLottoNumbers, bonusBall);
    }

    private void handleLottoResult(Map<Rank, Integer> rankMap, Payment payment) {
        outputView.printStatistics(new WinningStatistics(rankMap).toString());
        outputView.printYield(LottoCalculator.calculateYield(payment, rankMap));
    }

    private LottoNumbers getWinLottoNumbers() {
        return new LottoNumbers(inputView.getUserInputLottoNumbers().stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
    }

}
