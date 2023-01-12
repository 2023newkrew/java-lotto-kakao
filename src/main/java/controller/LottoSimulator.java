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
        WinningStatistics winningStatistics = new WinningStatistics(gameResult, user.getWallet().getUsage());
        outputView.printWinningStatistics(winningStatistics);
    }

    private WinningLotto getLastWinningLotto() {
        return inputView.getLastWinningLotto();
    }

    private User getUserInfo() {
        Wallet purchasePrice = new Wallet(inputView.getPurchasePrice());

        int manualLottoCountToPurchase = inputView.getManualLottoCountToPurchase();
        List<List<LottoNumber>> lottoNumbers = inputView.getManualLottoNumbers(manualLottoCountToPurchase);

        List<LottoTicket> manualLottoTickets = lottoTicketStore.purchaseLotto(lottoNumbers, purchasePrice);
        List<LottoTicket> autoLottoTickets = lottoTicketStore.purchaseLotto(purchasePrice);

        return new User(purchasePrice, manualLottoTickets, autoLottoTickets);
    }
}
