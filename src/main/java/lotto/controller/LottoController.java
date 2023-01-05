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
        LottoCount lottoCount = new LottoCount(price / LottoSettings.PRICE.getValue());
        Integer manualLottoCount = inputView.inputManualLottoCount();

        LottoList lottoList = issueLotto(lottoCount, manualLottoCount);

        List<Integer> winningLottoNumbers = inputView.inputWinningLottoNumbers();
        Integer bonusNumber = inputView.inputBonusBall();

        getResult(winningLottoNumbers, bonusNumber, lottoList, price);
    }

    public LottoList issueLotto(LottoCount lottoCount, Integer manualLottoCount) {
        lottoCount.validateManualLottoCount(manualLottoCount);
        LottoList manualLottoList = issueManualLotto(manualLottoCount);

        LottoList randomLottoList = Issuer.issueRandomLotto(lottoCount.get() - manualLottoCount);

        LottoList lottoList = manualLottoList.union(randomLottoList);
        outputView.printLottoCount(lottoCount, manualLottoCount);
        outputView.printLottoList(lottoList);

        return lottoList;
    }

    public LottoList issueManualLotto(Integer manualLottoCount) {
        LottoList manualLottoList = new LottoList();
        inputView.messageRequireInputManualLottoNumbers();

        for (int count = 0; count < manualLottoCount; count++) {
            List<Integer> manualLotto = inputView.inputManualLottoNumbers();
            manualLottoList.add(Issuer.issueManualLotto(manualLotto));
        }

        return manualLottoList;
    }

    public void getResult(List<Integer> winningLottoNumbers, Integer bonusNumber, LottoList lottoList, Integer price) {
        WinningNumbers winningNumbers = new WinningNumbers(winningLottoNumbers, bonusNumber);
        LottoStatistics lottoStatistics = new LottoStatistics();

        for (int i = 0; i < lottoList.length(); i++) {
            LottoResult lottoResult = LottoResult.match(winningNumbers.match(lottoList.get(i)));
            lottoStatistics.put(lottoResult);
        }

        outputView.printLottoStatistics(lottoStatistics);

        outputView.printRateOfProfit(lottoStatistics, price);
    }
}
