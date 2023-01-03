package lotto.controller;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    private final InputView inputView = new InputView();

    private final OutputView outputView = new OutputView();

    public void start() {
        Integer price = inputView.inputPrice();
        Integer lottoCount = price / LottoSettings.PRICE.getValue();
        outputView.printLottoCount(lottoCount);

        LottoList lottoList = Issuer.issue(lottoCount);
        outputView.printLottoList(lottoList);

        List<Integer> mainNumbers = inputView.inputMainNumbers();
        Integer bonusNumber = inputView.inputBonusBall();

        getResult(mainNumbers, bonusNumber, lottoList, price);
    }

    public void getResult(List<Integer> mainNumbers, Integer bonusNumber, LottoList lottoList, Integer price) {
        WinningNumbers winningNumbers = new WinningNumbers(mainNumbers, bonusNumber);
        LottoStatistics lottoStatistics = new LottoStatistics();

        for (int i = 0; i < lottoList.length(); i++) {
            LottoResult lottoResult = LottoResult.match(winningNumbers.check(lottoList.get(i)));
            lottoStatistics.put(lottoResult);
        }

        outputView.printLottoStatistics(lottoStatistics);

        outputView.printRateOfProfit(lottoStatistics, price);
    }
}
