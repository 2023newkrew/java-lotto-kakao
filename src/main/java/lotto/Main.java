package lotto;

import lotto.domain.*;
import lotto.view.LottoView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    private static final LottoView lottoView = new LottoView();
    private static final Seller seller = new Seller();
    private static Player player;

    public static void main(String[] args) {
        try (lottoView) {
            initializePlayer();
            buyLottoTickets();
            WinnerCombination winnerCombination = getWinnerCombination();
            PlayerLottoResult playerLottoResult = player.findResult(winnerCombination);
            lottoView.printStats(playerLottoResult);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        try {
            // 수동 로또 구매
            int manualLottoTicketsCount = lottoView.getManualLottoTicketsCount();
            seller.checkHasEnoughMoneyForManualLottoTickets(manualLottoTicketsCount, player.getCurrentMoney());
            player.buyManualLottoTickets(seller, getManualLottoTickets(manualLottoTicketsCount));
            // 남은 돈으로 자동 로또 구매
            player.buyAutoLottoTickets(seller);
            // 로또 결과 초기화
            player.initPlayerLottoResult();
            // 구매한 로또 숫자 출력
            lottoView.printPurchaseTickets(player.getLottoTickets());
        } catch (IllegalArgumentException e) {
            lottoView.printErrorMessage(e.getMessage());
            buyLottoTickets();
        }
    }

    //TODO: 메서드 분리해야 함.
    private static List<LottoTicket> getManualLottoTickets(int manualLottoTicketsCount) {
        try {
            List<LottoTicket> lottoTickets = new ArrayList<>();
            lottoView.printManualLottoTicketsInputMessage();
            for (int i = 0; i < manualLottoTicketsCount; i++) {
                String manualLottoTicketsInput = lottoView.getManualLottoTickets();
                Set<LottoBall> lottoBalls = Arrays.stream(manualLottoTicketsInput.split(","))
                        .map(number -> Integer.parseInt(number.trim()))
                        .map(number -> new LottoBall(number))
                        .collect(Collectors.toSet());
                lottoTickets.add(new LottoTicket(lottoBalls));
            }
            return lottoTickets;
        } catch (IllegalArgumentException e) {
            lottoView.printErrorMessage(e.getMessage());
            return getManualLottoTickets(manualLottoTicketsCount);
        }
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
