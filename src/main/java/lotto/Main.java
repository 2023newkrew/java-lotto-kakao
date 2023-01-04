package lotto;

import lotto.domain.*;
import lotto.view.LottoView;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    private static final LottoView lottoView = new LottoView();
    private static final Seller seller = new Seller();
    private static Player player;

    public static void main(String[] args) {
        initializePlayer();
        buyLottoTickets();
        WinnerCombination winnerCombination = getWinnerCombination();
        PlayerLottoResult playerLottoResult = player.findResult(winnerCombination);
        lottoView.printStats(playerLottoResult);
    }

    private static void initializePlayer() {
        int purchaseMoneyAmount = lottoView.getPurchaseMoneyAmount();
        try {
            player = new Player(new Money(purchaseMoneyAmount));
        } catch (IllegalArgumentException e) {
            lottoView.printErrorMessage(e.getMessage());
            initializePlayer();
        }
    }

    private static void buyLottoTickets() {
        player.buyLottoTickets(seller);
        lottoView.printPurchaseTickets(player.getLottoTickets());
    }

    private static WinnerCombination getWinnerCombination() {
        LottoTicket lottoTicket = getWinnerTicket();
        LottoBall bonusBall = getBonusBall();
        return new WinnerCombination(lottoTicket, bonusBall);
    }

    private static LottoTicket getWinnerTicket() {
        try {
            String winnerTicketInput = lottoView.getWinnerTicket();
            Set<LottoBall> lottoBalls = Arrays.stream(winnerTicketInput.split(","))
                    .map(number -> Integer.parseInt(number.trim()))
                    .map(number -> new LottoBall(number))
                    .collect(Collectors.toSet());
            return new LottoTicket(lottoBalls);
        } catch (IllegalArgumentException e) {
            lottoView.printErrorMessage(e.getMessage());
            return getWinnerTicket();
        }
    }

    private static LottoBall getBonusBall() {
        try {
            String bonusBallInput = lottoView.getBonusBall();
            return new LottoBall(Integer.parseInt(bonusBallInput));
        } catch (IllegalArgumentException e) {
            lottoView.printErrorMessage(e.getMessage());
            return getBonusBall();
        }
    }
}
