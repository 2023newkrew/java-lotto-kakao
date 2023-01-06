package lotto.controller;

import lotto.model.prize.PrizeRecord;
import lotto.model.service.LottoPublisher;
import lotto.model.service.Trader;
import lotto.model.service.TradingCalculator;
import lotto.model.ticket.LottoTicket;
import lotto.model.prize.WinningNumbers;
import lotto.model.ticket.LottoTickets;
import lotto.view.*;

public class LottoController {
    public void start() {
        Trader trader = this.newTrader();
        this.purchaseTickets(trader);
        this.confirmResult(trader);
    }

    private Trader newTrader() {
        long capital = 0L;
        while (capital < LottoTicket.PRICE) {
            capital = InputView.getCapital(LottoTicket.PRICE);
        }
        return new Trader(capital);
    }

    private void purchaseTickets(Trader trader) {
        int manualQuantity = -1;
        while (manualQuantity < 0) {
            manualQuantity = InputView.getManualQuantity();
        }

        LottoPublisher lottoPublisher = new LottoPublisher();
        LottoTickets manualTickets = lottoPublisher.publishManualLotto(InputView.getManualNumbersList(manualQuantity));
        trader.purchaseLotto(manualTickets);

        int automaticQuantity = trader.getFullPurchaseQuantity();
        LottoTickets automaticTickets = lottoPublisher.publishRandomLotto(automaticQuantity);
        trader.purchaseLotto(automaticTickets);

        OutputView.displayPurchasedTickets(manualQuantity, trader.getPurchasedTickets());
    }

    private void confirmResult(Trader trader) {
        WinningNumbers winningNumbers = new WinningNumbers(
                InputView.getWinningNumbers(),
                InputView.getBonusNumber()
        );

        PrizeRecord prizeRecord = trader.confirmResult(winningNumbers);
        OutputView.displayStatics(prizeRecord);
        OutputView.displayYield(TradingCalculator.calculateYield(prizeRecord));
    }
}
