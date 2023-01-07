package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class QuickPickSimulator implements LottoSimulator{
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoTicketStore lottoTicketStore;

    public QuickPickSimulator(InputView inputView, OutputView outputView, LottoTicketStore lottoTicketStore){
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoTicketStore = lottoTicketStore;
    }

    public void simulate(){
        User user = getUserInfo();
        outputView.printUserInfo(user);

        WinningLotto lastWinningLotto = getLastWinningLotto();

        LottoGame lottoGame = new LottoGame(user, lastWinningLotto);
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

//    public void asdas() {
//        User user = getGameConfig();
//        outputView.printUserInfo(user);
//
//        LottoGame lottoGame = new LottoGame(user);
//        GameResult gameResult = lottoGame.play();
//
//        outputView.printGameResult(gameResult);
//    }
//
//    private User getGameConfig() {
//        int purchasePrice = getPurchasePrice();
//        int purchaseAvailLottoTicketCount = getPurchaseAvailLottoTicketCount(purchasePrice);
//
//        List<LottoTicket> manualTickets = getManualLottoTickets(purchaseAvailLottoTicketCount);
//
//        int autoTicketCount = purchaseAvailLottoTicketCount - manualTickets.size();
//        List<LottoTicket> autoTickets = getAutoTickets(autoTicketCount);
//
//        WinningLotto lastWinningLotto = getLastWinningLotto();
//
//        return new User(purchasePrice, manualTickets, autoTickets, lastWinningLotto);
//    }
//
//    private static int getPurchaseAvailLottoTicketCount(int purchasePrice) {
//        return purchasePrice / LottoConstant.LOTTO_PRICE;
//    }
//
//    private List<LottoTicket> getManualLottoTickets(int purchaseAvailLottoCount) {
//        int manualLottoCountToPurchase = inputView.getManualLottoCountToPurchase();
//
//        if(purchaseAvailLottoCount < manualLottoCountToPurchase){
//            throw new IllegalArgumentException("구입금액이 부족합니다.");
//        }
//
//        return inputView.getManualLottoNumbers(manualLottoCountToPurchase);
//    }
//
//    private List<LottoTicket> getAutoTickets(int autoLottoCount) {
//        return lottoTicketGenerator.generate(autoLottoCount);
//    }
}
