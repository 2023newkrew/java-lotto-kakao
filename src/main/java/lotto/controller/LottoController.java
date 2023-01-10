package lotto.controller;

import lotto.domain.UserAccount;
import lotto.domain.lotto.LottoResultCalculator;
import lotto.domain.lotto.LottoWinningNumber;
import lotto.domain.lotto.store.LottoStore;
import lotto.domain.lotto.ticket.LottoTicket;
import lotto.domain.lotto.ticket.generator.LottoTicketManualGenerator;
import lotto.domain.lotto.ticket.generator.LottoTicketRandomGenerator;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private final LottoInputView lottoInputView = new LottoInputView();
    private final LottoOutputView lottoOutputView = new LottoOutputView();
    private final LottoStore lottoStore = new LottoStore();

    public void startLotto() {
        UserAccount userAccount = lottoInputView.readUserAccount();
        List<LottoTicket> lottoTickets = new ArrayList<>();
        int manualLottoAmount = lottoInputView.readBuyingAmount();
        lottoOutputView.printBuyingManualLottoMessage();
        lottoTickets.addAll(
                lottoStore.buyLottoTicket(userAccount, manualLottoAmount, new LottoTicketManualGenerator())
        );
        int randomLottoAmount = lottoStore.calculateAvailablePurchases(userAccount);
        lottoTickets.addAll(
                lottoStore.buyLottoTicket(userAccount, randomLottoAmount, new LottoTicketRandomGenerator())
        );
        lottoOutputView.printBuyingAmounts(manualLottoAmount, randomLottoAmount);
        lottoOutputView.printLottoTickets(lottoTickets);
        LottoWinningNumber lottoWinningNumber = lottoInputView.readWinningNumber();
        LottoResultCalculator lottoResultCalculator = new LottoResultCalculator(lottoTickets, lottoWinningNumber);
        lottoOutputView.printLottoResult(lottoResultCalculator.calculateLottoResult());
    }
}
