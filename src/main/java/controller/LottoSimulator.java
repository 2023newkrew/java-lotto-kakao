package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoSimulator{
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoTicketStore lottoTicketStore;
    private final User user;

    public LottoSimulator(InputView inputView, OutputView outputView, LottoTicketStore lottoTicketStore){
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoTicketStore = lottoTicketStore;
        this.user = new User();
    }

    public void simulate(){
        initGame();
        outputView.printUserInfo(user);

        WinningLotto lastWinningLotto = getLastWinningLotto();

        LottoGame lottoGame = new LottoGame(user.getLottoTickets(), lastWinningLotto);
        GameResult gameResult = lottoGame.play();

        WinningStatistics winningStatistics = new WinningStatistics(gameResult, user.getMoneyUsage());
        outputView.printWinningStatistics(winningStatistics);
    }

    private void initGame() {
        initUserAmount();
        initUserLottoTicket();
    }

    private void initUserLottoTicket() {
        int manualLottoTicketCountToPurchase = inputView.getManualLottoCountToPurchase();
        List<List<LottoNumber>> lottoNumbers = inputView.getManualLottoNumbers(manualLottoTicketCountToPurchase);
        user.buyManualLottoTicket(lottoTicketStore, lottoNumbers, manualLottoTicketCountToPurchase);

        int autoLottoTicketCountToPurchase = (user.getRemainAmount()) / LottoTicketStore.AUTO_LOTTO_TICKET_COST;
        user.buyAutoLottoTicket(lottoTicketStore, autoLottoTicketCountToPurchase);
    }

    private void initUserAmount() {
        int userAmount = inputView.getUserAmount();
        user.receiveMoney(userAmount);
    }

    private WinningLotto getLastWinningLotto() {
        return inputView.getLastWinningLotto();
    }
}
