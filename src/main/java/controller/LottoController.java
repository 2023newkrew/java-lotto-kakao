package controller;

import domain.UserAccount;
import domain.lotto.LottoResultCalculator;
import domain.lotto.LottoWinningNumber;
import domain.lotto.store.LottoStore;
import domain.lotto.store.dto.LottoTicketBuyRequestDto;
import domain.lotto.store.dto.LottoTicketBuyResponseDto;
import domain.lotto.ticket.LottoTicket;
import domain.lotto.ticket.generator.LottoTicketGenerator;
import domain.lotto.ticket.generator.LottoTicketRandomGenerator;
import view.LottoInputView;
import view.LottoOutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private final LottoInputView lottoInputView = new LottoInputView();
    private final LottoOutputView lottoOutputView = new LottoOutputView();
    private final LottoStore lottoStore = new LottoStore();
    private final LottoTicketGenerator lottoTicketGenerator = new LottoTicketRandomGenerator();

    public void startLotto() {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        buyAsManyLottos(lottoTickets);
        lottoOutputView.printLottoTickets(lottoTickets);
        LottoWinningNumber lottoWinningNumber = lottoInputView.readWinningNumber();
        LottoResultCalculator lottoResultCalculator = new LottoResultCalculator(lottoTickets, lottoWinningNumber);
        lottoOutputView.printLottoResult(lottoResultCalculator.calculateLottoResult());
    }

    private void buyAsManyLottos(List<LottoTicket> lottoTickets) {
        UserAccount userAccount = lottoInputView.readUserAccount();
        while (lottoStore.canBuyLotto(userAccount)) {
            LottoTicketBuyRequestDto request = new LottoTicketBuyRequestDto(userAccount, lottoTicketGenerator.generate());
            LottoTicketBuyResponseDto response = lottoStore.buyLottoTicket(request);
            userAccount = response.getUserAccount();
            lottoTickets.add(response.getLottoTicket());
        }
    }
}
