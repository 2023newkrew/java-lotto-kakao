package lotto.controller;

import lotto.domain.UserAccount;
import lotto.domain.lotto.LottoResultCalculator;
import lotto.domain.lotto.LottoWinningNumber;
import lotto.domain.lotto.store.LottoStore;
import lotto.domain.lotto.ticket.LottoTicket;
import lotto.domain.lotto.ticket.generator.LottoTicketGenerator;
import lotto.domain.lotto.ticket.generator.LottoTicketRandomGenerator;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private final LottoInputView lottoInputView = new LottoInputView();
    private final LottoOutputView lottoOutputView = new LottoOutputView();
    private final LottoStore lottoStore = new LottoStore();
    private final LottoTicketGenerator lottoTicketGenerator = new LottoTicketRandomGenerator();

    public void startLotto() {
        List<LottoTicket> lottoTickets = buyLottoTickets();
        lottoOutputView.printLottoTickets(lottoTickets);
        LottoWinningNumber lottoWinningNumber = lottoInputView.readWinningNumber();
        LottoResultCalculator lottoResultCalculator = new LottoResultCalculator(lottoTickets, lottoWinningNumber);
        lottoOutputView.printLottoResult(lottoResultCalculator.calculateLottoResult());
    }

    private List<LottoTicket> buyLottoTickets() {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        UserAccount userAccount = lottoInputView.readUserAccount();
        int manualLottoAmount = buyManualLotto(userAccount, lottoTickets);
        int autoLottoAmount = buyAutoLotto(userAccount, lottoTickets);
        lottoOutputView.printBuyingAmounts(manualLottoAmount, autoLottoAmount);
        return lottoTickets;
    }

    private int buyManualLotto(UserAccount userAccount, List<LottoTicket> lottoTickets) {
        int buyingAmount = lottoInputView.readBuyingAmount();
        lottoOutputView.printBuyingManualLottoMessage();
        for (int i = 0; i < buyingAmount; i++) {
            LottoTicket lottoTicket = lottoStore.buyLottoTicket(userAccount, lottoInputView.readLottoNumbers());
            lottoTickets.add(lottoTicket);
        }
        return buyingAmount;
    }

    private int buyAutoLotto(UserAccount userAccount, List<LottoTicket> lottoTickets) {
        int buyingAmount = 0;
        while (lottoStore.canBuyLotto(userAccount)) {
            LottoTicket lottoTicket = lottoStore.buyLottoTicket(userAccount, lottoTicketGenerator.generate());
            lottoTickets.add(lottoTicket);
            buyingAmount++;
        }
        return buyingAmount;
    }
}
