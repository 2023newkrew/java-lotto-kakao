package lotto.controller;

import lotto.model.store.PurchaseResult;
import lotto.model.ranking.WinningNumber;
import lotto.model.ranking.LottoStats;
import lotto.model.store.LottoReceipt;
import lotto.model.store.LottoStore;
import lotto.model.store.Money;
import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTicket;
import lotto.model.ticket.SingleLottoNumber;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;

import java.math.BigDecimal;

public class LottoSimulator {

    private static final Money LOTTO_PRICE = Money.valueOf(1000L);

    private final LottoInputView inputView = new LottoInputView();

    private final LottoOutputView outputView = new LottoOutputView();

    public void run() {
        Money money = inputView.inputMoney();
        LottoStore store = LottoStore.create(LOTTO_PRICE);
        PurchaseResult purchaseResult = store.buyAutomatically(money);
        LottoTicket ticket = purchaseResult.getTicket();
        LottoReceipt receipt = purchaseResult.getReceipt();
        outputView.printTicket(ticket);
        WinningNumber winningNumber = createWinningNumber();
        LottoStats stats = ticket.analyze(winningNumber);
        BigDecimal profitRate = receipt.calculateProfitRate(stats.getTotalPrize());
        outputView.printResult(stats, profitRate);
    }

    private WinningNumber createWinningNumber() {
        LottoNumber winningNumber = inputView.inputWinningNumber();
        SingleLottoNumber bonus = inputView.inputBonus();

        return WinningNumber.from(winningNumber, bonus);
    }
}
