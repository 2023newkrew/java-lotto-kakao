package lotto.controller;

import lotto.model.Issuer;
import lotto.model.LottoList;
import lotto.model.LottoStatistics;
import lotto.model.WinningNumbers;
import lotto.model.enums.LottoResult;
import lotto.model.enums.LottoSettings;
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

        getResult(new WinningNumbers(mainNumbers, bonusNumber), lottoList, price);
    }

    public void getResult(WinningNumbers winningNumbers, LottoList lottoList, Integer price) {
        LottoStatistics lottoStatistics = new LottoStatistics();

        for (int i = 0; i < lottoList.length(); i++) {
            LottoResult lottoResult = LottoResult.match(winningNumbers.check(lottoList.get(i)));
            lottoStatistics.put(lottoResult);
        }

        outputView.printLottoStatistics(lottoStatistics);

        outputView.printRateOfProfit(lottoStatistics, price);
    }
}
