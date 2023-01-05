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
            int manualLottoTicketsCount = lottoView.getManualLottoTicketsCount();
            // 수동 로또 구매
            buyManualLottoTickets(manualLottoTicketsCount);
            // 남은 돈으로 자동 로또 구매
            player.buyAutoLottoTickets(seller);
            // 로또 결과 초기화
            player.initPlayerLottoResult();
            // 구매한 로또 숫자 출력
            lottoView.printPurchaseTickets(manualLottoTicketsCount, player.getLottoTickets());
        } catch (IllegalArgumentException e) {
            lottoView.printErrorMessage(e.getMessage());
            buyLottoTickets();
        }
    }

    private static void buyManualLottoTickets(int manualLottoTicketsCount) {
        // 입력한 갯수만큼 수동 로또 구매를 할 수 있는 충분한 돈이 있는지 확인
        seller.checkHasEnoughMoneyForManualLottoTickets(manualLottoTicketsCount, player.getCurrentMoney());
        // 구매 진행
        player.buyManualLottoTickets(seller, getManualLottoTickets(manualLottoTicketsCount));
    }

    private static List<LottoTicket> getManualLottoTickets(int manualLottoTicketsCount) {
        try {
            lottoView.printManualLottoTicketsInputMessage();
            return getManualLottoTicketsInput(manualLottoTicketsCount);
        } catch (IllegalArgumentException e) {
            lottoView.printErrorMessage(e.getMessage());
            return getManualLottoTickets(manualLottoTicketsCount);
        }
    }

    private static List<LottoTicket> getManualLottoTicketsInput(int manualLottoTicketsCount) {
        List<LottoTicket> lottoTickets = new ArrayList<>();

        for (int i = 0; i < manualLottoTicketsCount; i++) {
            String input = lottoView.getManualLottoTickets();
            Set<LottoBall> lottoBalls = Arrays.stream(input.split(","))
                    .map(number -> Integer.parseInt(number.trim()))
                    .map(number -> new LottoBall(number))
                    .collect(Collectors.toSet());
            lottoTickets.add(new LottoTicket(lottoBalls));
        }

        return lottoTickets;
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
