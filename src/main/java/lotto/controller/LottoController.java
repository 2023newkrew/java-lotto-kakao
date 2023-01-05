package lotto.controller;

import lotto.model.*;
import lotto.model.enums.LottoResult;
import lotto.model.strategy.ManualLottoStrategy;
import lotto.model.strategy.RandomAutomaticLottoStrategy;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.stream.IntStream;

public class LottoController {

    private final InputView inputView = new InputView();

    private final OutputView outputView = new OutputView();

    private final LottoIssuer automaticLottoIssuer = new LottoIssuer(new RandomAutomaticLottoStrategy());

    private final LottoIssuer manualLottoIssuer = new LottoIssuer(new ManualLottoStrategy());

    public void start() {
        Integer price = inputView.inputPrice();
        Integer totalLottoCount = price / Lotto.PRICE;

        Integer manualLottoCount = inputView.inputManualLottoCount();
        Integer automaticLottoCount = totalLottoCount - manualLottoCount;

        outputView.printRequestManualLottoNumber();
        LottoList manualLottoList = manualLottoIssuer.issue(manualLottoCount);

        LottoList automaticLottoList = automaticLottoIssuer.issue(automaticLottoCount);
        LottoList totalLottoList = manualLottoList.merge(automaticLottoList);
        outputView.printLottoCount(manualLottoCount, automaticLottoCount);
        outputView.printLottoList(totalLottoList);

        List<Integer> mainNumbers = inputView.inputMainNumbers();
        Integer bonusNumber = inputView.inputBonusBall();

        getResult(new WinningNumbers(mainNumbers, bonusNumber), totalLottoList, price);
    }

    private void getResult(WinningNumbers winningNumbers, LottoList lottoList, Integer price) {
        LottoStatistics lottoStatistics = new LottoStatistics();

        IntStream.range(0, lottoList.length())
                .forEach(i -> lottoStatistics.put(LottoResult.match(winningNumbers.check(lottoList.get(i)))));

        outputView.printLottoStatistics(lottoStatistics);

        outputView.printRateOfProfit(lottoStatistics, price);
    }
}
