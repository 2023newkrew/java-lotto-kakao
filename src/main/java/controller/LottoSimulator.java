package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoSimulator{
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoTicketStore lottoTicketStore;

    public LottoSimulator(InputView inputView, OutputView outputView, LottoTicketStore lottoTicketStore){
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoTicketStore = lottoTicketStore;
    }

    public void simulate(){
        User user = getUserInfo();
        outputView.printUserInfo(user);

        WinningLotto lastWinningLotto = getLastWinningLotto();

        LottoGame lottoGame = new LottoGame(user.getLottoTickets(), lastWinningLotto);
        GameResult gameResult = lottoGame.play();
        WinningStatistics winningStatistics = new WinningStatistics(gameResult, user.getMoneyUsage());
        outputView.printWinningStatistics(winningStatistics);
    }

    private WinningLotto getLastWinningLotto() {
        return inputView.getLastWinningLotto();
    }

    private User getUserInfo() {
        int userAmount = inputView.getUserAmount();
        User user = new User(userAmount);

        int manualLottoTicketCountToPurchase = inputView.getManualLottoCountToPurchase();
        List<List<LottoNumber>> lottoNumbers = inputView.getManualLottoNumbers(manualLottoTicketCountToPurchase);
        user.buyManualLottoTicket(lottoTicketStore, lottoNumbers, manualLottoTicketCountToPurchase);

        int autoLottoTicketCountToPurchase = (user.getRemainAmount()) / LottoTicketStore.AUTO_LOTTO_TICKET_COST;
        user.buyAutoLottoTicket(lottoTicketStore, autoLottoTicketCountToPurchase);

        return user;
    }
}
