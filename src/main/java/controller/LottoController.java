package controller;

import domain.*;
import view.InputView;
import view.OutputView;
import view.WinningStatistics;
import utils.LottoCalculator;
import utils.LottoGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoController {
    private static final String LOTTO_NUMBER_DELIMITER = ", ";

    private InputView inputView;
    private OutputView outputView;

    public LottoController() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void run() {
        Payment payment = new Payment(Integer.parseInt(inputView.getUserInputPayment()));
        outputView.printNumberOfLotto(LottoCalculator.calculateNumberOfLotto(payment));

        List<LottoNumbers> lottoNumbersList = LottoGenerator.generateLotto(payment);
        String purchasedLotto = lottoNumbersList.stream()
                .map(LottoNumbers::toString)
                .collect(Collectors.joining("\n"));
        outputView.printLotto(purchasedLotto);

        execute(payment, lottoNumbersList);
    }

    public void execute(Payment payment, List<LottoNumbers> lottoNumbersList) {
        LottoNumbers winLottoNumbers = new LottoNumbers(Arrays.stream(inputView.getUserInputLottoNumbers().split(LOTTO_NUMBER_DELIMITER))
                .map(number -> new LottoNumber(Integer.parseInt(number)))
                .collect(Collectors.toList()));
        LottoNumber bonusBall = new LottoNumber(Integer.parseInt(inputView.getUserInputBonusBallNumbers()));

        Lotto lotto = new Lotto(lottoNumbersList);
        Map<Rank, Integer> rankMap = lotto.rankEachLotto(winLottoNumbers, bonusBall);

        outputView.printStatistics(new WinningStatistics(rankMap).toString());
        outputView.printYield(LottoCalculator.calculateYield(payment, rankMap));
    }

}
