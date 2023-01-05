package lottov2.controller;

import lottov2.model.campany.LottoCompany;
import lottov2.model.campany.LottoStats;
import lottov2.model.store.LottoStore;
import lottov2.model.store.LottoWallet;
import lottov2.model.store.Money;
import lottov2.model.ticket.LottoNumber;
import lottov2.model.ticket.LottoTicket;
import lottov2.model.ticket.SingleLottoNumber;
import lottov2.view.LottoInputView;
import lottov2.view.LottoOutputView;

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
        LottoCompany company = createLottoCompany();

        return company.analyze(ticket);
    }

    private LottoCompany createLottoCompany() {
        LottoNumber winningNumber = inputView.inputWinningNumber();
        SingleLottoNumber bonus = inputView.inputBonus();

        return LottoCompany.create(winningNumber, bonus);
    }

    private static BigDecimal getProfitRate(LottoWallet wallet, LottoStats stats) {
        Money totalPrize = stats.getTotalPrize();

        return wallet.getProfitRate(totalPrize);
    }
}
