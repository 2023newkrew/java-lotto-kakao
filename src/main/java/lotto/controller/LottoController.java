package lotto.controller;

import lotto.domain.*;
import lotto.view.LottoView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    private final LottoView lottoView;

    public LottoController() {
        this.lottoView = new LottoView();
    }

    public void play() {
        Player player = initializePlayer();
        WinnerCombination winnerCombination = getWinnerCombination();
        announceResult(player, winnerCombination);
    }

    private Player initializePlayer() {
        try {
            int purchaseMoneyAmount = lottoView.getPurchaseMoneyAmount();
            Player player = new Player(purchaseMoneyAmount);
            LottoTicketSeller seller = new LottoTicketSeller();
            player.buyLottoTickets(seller);
            lottoView.printPurchaseTickets(player.getLottoTickets());
            return player;
        } catch (IllegalArgumentException e) {
            lottoView.printErrorMessage(e.getMessage());
            return initializePlayer();
        }
    }

    private WinnerCombination getWinnerCombination() {
        try {
            LottoTicket lottoTicket = getWinnerTicket();
            LottoBall bonusBall = getBonusBall();
            return new WinnerCombination(lottoTicket, bonusBall);
        } catch (IllegalArgumentException e) {
            lottoView.printErrorMessage(e.getMessage());
            return getWinnerCombination();
        }
    }

    private LottoTicket getWinnerTicket() {
        String winnerTicketInput = lottoView.getWinnerTicket();
        List<LottoBall> lottoBalls = Arrays.stream(winnerTicketInput.split(","))
                .map(number -> Integer.parseInt(number.trim()))
                .map(LottoBall::new)
                .collect(Collectors.toList());
        return new LottoTicket(lottoBalls);
    }

    private LottoBall getBonusBall() {
        String bonusBallInput = lottoView.getBonusBall();
        return new LottoBall(Integer.parseInt(bonusBallInput));
    }

    private void announceResult(Player player, WinnerCombination winnerCombination) {
        PlayerLottoResult playerLottoResult = player.findResult(winnerCombination);
        lottoView.printStats(playerLottoResult);
    }
}
