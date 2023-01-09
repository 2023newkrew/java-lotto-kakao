package lotto.controller;

import lotto.model.prize.PrizeRecord;
import lotto.model.publisher.LottoPublisher;
import lotto.model.trader.Trader;
import lotto.model.trader.TradingCalculator;
import lotto.model.prize.WinningNumbers;
import lotto.model.ticket.LottoTickets;
import lotto.view.*;

public class LottoController {
    public void start() {
        Trader trader = new Trader(InputView.getCapital());
        LottoPublisher lottoPublisher = new LottoPublisher();
        this.purchaseTickets(trader, lottoPublisher);
        this.confirmResult(trader);
    }

    private void purchaseManualTickets(Trader trader, LottoPublisher lottoPublisher) {
        int manualQuantity = InputView.getManualQuantity();
        if (manualQuantity < 0) {
            throw new IllegalArgumentException("올바른 매수를 입력해주세요.");
        }
        if (manualQuantity > 0) {
            LottoTickets manualTickets = lottoPublisher.publishManualLotto(InputView.getManualNumbersList(manualQuantity));
            trader.purchaseLotto(manualTickets);
        }
    }

    private int purchaseAutomaticTickets(Trader trader, LottoPublisher lottoPublisher) {
        int automaticQuantity = trader.getFullPurchaseQuantity();
        LottoTickets automaticTickets = lottoPublisher.publishRandomLotto(automaticQuantity);
        trader.purchaseLotto(automaticTickets);
        return automaticQuantity;
    }

    private void purchaseTickets(Trader trader, LottoPublisher lottoPublisher) {
        this.purchaseManualTickets(trader, lottoPublisher);
        int automaticQuantity = this.purchaseAutomaticTickets(trader, lottoPublisher);
        OutputView.displayPurchasedTickets(automaticQuantity, trader.getPurchasedTickets());
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
