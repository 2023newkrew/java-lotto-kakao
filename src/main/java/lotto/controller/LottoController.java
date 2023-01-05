package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoTicketSeller seller;
    private Player player;
    private WinnerCombination winnerCombination;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.seller = new LottoTicketSeller();
    }

    public void play() {
        initializePlayer();
        buyLotto();
        getWinnerCombination();
        announceResult();
    }

    private void initializePlayer() {
        try {
            int purchaseMoneyAmount = inputView.getPurchaseMoneyAmount();
            player = new Player(purchaseMoneyAmount);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            initializePlayer();
        }
    }

    private void buyLotto() {
        try {
            int manualLottoCount = inputView.getManualLottoCount();
            List<List<Integer>> manualLottoNumbers = inputView.getManualLottoNumbers(manualLottoCount);
            player.buyManualLottoTickets(seller, manualLottoNumbers);
            player.buyAutoLottoTickets(seller);
            outputView.printPurchasedTickets(player);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            buyLotto();
        }
    }

    private void getWinnerCombination() {
        try {
            LottoTicket lottoTicket = getWinnerTicket();
            LottoBall bonusBall = getBonusBall();
            winnerCombination = new WinnerCombination(lottoTicket, bonusBall);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            getWinnerCombination();
        }
    }

    private LottoTicket getWinnerTicket() {
        List<Integer> userInputNumber = inputView.getWinnerTicketNumbers();
        List<LottoBall> lottoBalls = userInputNumber.stream()
                .map(LottoBall::new)
                .collect(Collectors.toList());
        return new LottoTicket(lottoBalls);
    }

    private LottoBall getBonusBall() {
        int bonusBallNumber = inputView.getBonusBall();
        return new LottoBall(bonusBallNumber);
    }

    private void announceResult() {
        PlayerLottoResult playerLottoResult = player.findResult(winnerCombination);
        outputView.printStats(playerLottoResult);
    }
}
