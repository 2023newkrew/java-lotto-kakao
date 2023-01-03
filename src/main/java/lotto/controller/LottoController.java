package lotto.controller;

import lotto.model.*;
import lotto.view.*;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    public void start() {
        Exchange exchange = new Exchange();

        long purchaseAmount = 0;
        while (!Exchange.isEnough(purchaseAmount)) {
            purchaseAmount = InputView.getPurchaseAmount();
        }

        Lotto lotto = exchange.purchaseLotto(purchaseAmount);
        OutputView.sendPurchasedLotto(lotto);

        List<Integer> winningNumbers = new ArrayList<>();
        while (!WinningNumbers.isValidWinningNumbers(winningNumbers)) {
            winningNumbers = InputView.getWinningNumbers();
        }

        int bonusNumber = 0;
        while (!WinningNumbers.isValidBonusNumber(winningNumbers, bonusNumber)) {
            bonusNumber = InputView.getBonusNumber();
        }

        PrizeJudge prizeJudge = new PrizeJudge(new WinningNumbers(winningNumbers, bonusNumber));
        PrizeRecord prizeRecord = new PrizeRecord();

        for (Ticket ticket : lotto.getTickets()) {
            prizeRecord.addCountOf(prizeJudge.getPrizeOf(ticket));
        }

        OutputView.sendStatics(prizeRecord);
        OutputView.sendYield(exchange.calculateYield(prizeRecord));
    }
}
