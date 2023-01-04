package controller;

import domain.*;
import utils.InputValidator;
import utils.LottoConverter;
import view.InputView;
import view.OutputView;
import view.PurchasedLotto;
import view.WinningStatistics;
import utils.LottoCalculator;
import utils.LottoGenerator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void run() {
        Payment payment = new Payment(inputView.getPayment());

        int numberOfManualLotto = inputView.getNumberOfManualLotto();
        InputValidator.validateNumberOfManualLotto(payment, numberOfManualLotto);

        PurchasedLotto purchasedLotto = getPurchasedLotto(payment, numberOfManualLotto);
        outputView.printPurchasedLotto(purchasedLotto);

        LottoNumbers winLottoNumbers = LottoConverter.integerListToLottoNumbers(inputView.getWinningLottoNumbers());
        LottoNumber bonusBall = new LottoNumber(inputView.getBonusBallNumber());

        handleLottoResult(execute(purchasedLotto, winLottoNumbers, bonusBall), payment);
    }

    private void handleLottoResult(Map<Rank, Integer> rankMap, Payment payment) {
        outputView.printStatistics(new WinningStatistics(rankMap).toString());
        outputView.printYield(LottoCalculator.calculateYield(payment, rankMap));
    }

    private Map<Rank, Integer> execute(PurchasedLotto purchasedLotto, LottoNumbers winLottoNumbers, LottoNumber bonusBall) {
        Lotto lotto = new Lotto(purchasedLotto.getLottoNumbersList());

        return lotto.rankEachLotto(winLottoNumbers, bonusBall);
    }

    private PurchasedLotto getPurchasedLotto(Payment payment, int numberOfManualLotto) {
        int numberOfAutomaticLotto = LottoCalculator.calculateNumberOfLotto(payment) - numberOfManualLotto;
        List<LottoNumbers> lottoNumbersList = getManualLottoNumbers(numberOfManualLotto);
        lottoNumbersList.addAll(LottoGenerator.generateLotto(numberOfAutomaticLotto));

        return new PurchasedLotto(numberOfManualLotto, numberOfAutomaticLotto, lottoNumbersList);
    }

    private List<LottoNumbers> getManualLottoNumbers(int numberOfManualLotto) {
        return inputView.getManualLottoNumbers(numberOfManualLotto)
                .stream()
                .map(LottoConverter::integerListToLottoNumbers)
                .collect(Collectors.toList());
    }

}
