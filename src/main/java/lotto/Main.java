package lotto;

import lotto.domain.*;
import lotto.view.LottoView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    private static final Seller seller = new Seller();
    private static Player player;

    public static void main(String[] args) {
        try (LottoView lottoView = new LottoView()) {
            initializePlayer(lottoView);
            buyLottoTickets(lottoView);
            WinnerCombination winnerCombination = getWinnerCombination(lottoView);
            PlayerLottoResult playerLottoResult = player.findResult(winnerCombination);
            lottoView.printStats(playerLottoResult);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void initializePlayer(LottoView lottoView) {
        try {
            int purchaseMoneyAmount = Integer.parseInt(lottoView.getPurchaseMoneyAmount());
            player = new Player(new Money(purchaseMoneyAmount));
        } catch (IllegalArgumentException e) {
            lottoView.printErrorMessage(e.getMessage());
            initializePlayer(lottoView);
        }
    }

    private static void buyLottoTickets(LottoView lottoView) {
        try {
            int manualLottoTicketsCount = Integer.parseInt(lottoView.getManualLottoTicketsCount());
            // 수동 로또 구매
            buyManualLottoTickets(manualLottoTicketsCount, lottoView);
            // 남은 돈으로 자동 로또 구매
            player.buyAutoLottoTickets(seller);
            // 로또 결과 초기화
            player.initPlayerLottoResult();
            // 구매한 로또 숫자 출력
            lottoView.printPurchaseTickets(manualLottoTicketsCount, player.getLottoTickets());
        } catch (IllegalArgumentException e) {
            lottoView.printErrorMessage(e.getMessage());
            buyLottoTickets(lottoView);
        }
    }

    private static void buyManualLottoTickets(int manualLottoTicketsCount, LottoView lottoView) {
        // 입력한 갯수만큼 수동 로또 구매를 할 수 있는 충분한 돈이 있는지 확인
        seller.checkHasEnoughMoneyForManualLottoTickets(manualLottoTicketsCount, player.getCurrentMoney());
        // 구매 진행
        player.buyManualLottoTickets(seller, getManualLottoTickets(manualLottoTicketsCount, lottoView));
    }

    private static List<LottoTicket> getManualLottoTickets(int manualLottoTicketsCount, LottoView lottoView) {
        try {
            lottoView.printManualLottoTicketsInputMessage();
            return getManualLottoTicketsInput(manualLottoTicketsCount, lottoView);
        } catch (IllegalArgumentException e) {
            lottoView.printErrorMessage(e.getMessage());
            return getManualLottoTickets(manualLottoTicketsCount, lottoView);
        }
    }

    private static List<LottoTicket> getManualLottoTicketsInput(int manualLottoTicketsCount, LottoView lottoView) {
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

    private static WinnerCombination getWinnerCombination(LottoView lottoView) {
        LottoTicket lottoTicket = getWinnerTicket(lottoView);
        LottoBall bonusBall = getBonusBall(lottoView);
        return new WinnerCombination(lottoTicket, bonusBall);
    }

    private static LottoTicket getWinnerTicket(LottoView lottoView) {
        try {
            String winnerTicketInput = lottoView.getWinnerTicket();
            Set<LottoBall> lottoBalls = Arrays.stream(winnerTicketInput.split(","))
                    .map(number -> Integer.parseInt(number.trim()))
                    .map(number -> new LottoBall(number))
                    .collect(Collectors.toSet());
            return new LottoTicket(lottoBalls);
        } catch (IllegalArgumentException e) {
            lottoView.printErrorMessage(e.getMessage());
            return getWinnerTicket(lottoView);
        }
    }

    private static LottoBall getBonusBall(LottoView lottoView) {
        try {
            return new LottoBall(Integer.parseInt(lottoView.getBonusBall()));
        } catch (IllegalArgumentException e) {
            lottoView.printErrorMessage(e.getMessage());
            return getBonusBall(lottoView);
        }
    }
}
