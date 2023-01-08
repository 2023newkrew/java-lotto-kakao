package lotto.controller;

import lotto.model.ranking.LottoStats;
import lotto.model.store.LottoReceipt;
import lotto.model.store.LottoStore;
import lotto.model.store.LottoWallet;
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
        LottoWallet wallet = createWallet();
        LottoStore store = LottoStore.create(LOTTO_PRICE);
        LottoTicket ticket = buyLotto(wallet, store);
        outputView.printTicket(ticket);
        LottoStats stats = createLottoCompanyAndAnalyze(ticket);
        BigDecimal profitRate = getProfitRate(wallet, stats);
        outputView.printResult(stats, profitRate);
    }

    private LottoWallet createWallet() {
        Money money = inputView.inputMoney();

        return LottoWallet.create(money);
    }

    private static LottoTicket buyLotto(LottoWallet wallet, LottoStore store) {
        wallet.buyLottoTicketAutomatically(store);

        return wallet.getTicket();
    }

    private LottoStats createLottoCompanyAndAnalyze(LottoTicket ticket) {
        WinningNumber company = createLottoCompany();

        return ticket.analyze(company);
    }

    private WinningNumber createLottoCompany() {
        LottoNumber winningNumber = inputView.inputWinningNumber();
        SingleLottoNumber bonus = inputView.inputBonus();

        return WinningNumber.from(winningNumber, bonus);
    }

    private static BigDecimal getProfitRate(LottoWallet wallet, LottoStats stats) {
        Money totalPrize = stats.getTotalPrize();

        return wallet.getProfitRate(totalPrize);
    }
}
