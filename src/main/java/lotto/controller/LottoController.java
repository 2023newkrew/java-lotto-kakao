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

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.seller = new LottoTicketSeller();
    }

    public void play() {
        Player player = initializePlayer();
        WinnerCombination winnerCombination = getWinnerCombination();
        announceResult(player, winnerCombination);
    }

    private Player initializePlayer() {
        try {
            int purchaseMoneyAmount = inputView.getPurchaseMoneyAmount();
            Player player = new Player(purchaseMoneyAmount);
            player.buyLottoTickets(seller);
            outputView.printPurchasedTickets(player.getLottoTickets());
            return player;
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return initializePlayer();
        }
    }

    private WinnerCombination getWinnerCombination() {
        try {
            LottoTicket lottoTicket = getWinnerTicket();
            LottoBall bonusBall = getBonusBall();
            return new WinnerCombination(lottoTicket, bonusBall);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return getWinnerCombination();
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
        String bonusBallInput = inputView.getBonusBall();
        return new LottoBall(Integer.parseInt(bonusBallInput));
    }

    private void announceResult(Player player, WinnerCombination winnerCombination) {
        PlayerLottoResult playerLottoResult = player.findResult(winnerCombination);
        outputView.printStats(playerLottoResult);
    }
}
