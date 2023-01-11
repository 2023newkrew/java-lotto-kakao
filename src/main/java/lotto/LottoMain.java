package lotto;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.ResultView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMain {
    private static final int LOTTO_PRICE = 1000;

    static final InputView iv = InputView.getInstance();
    static final ResultView rv = ResultView.getInstance();


    public static void main(String[] args) {
        int money = getMoney();
        int count = money / LOTTO_PRICE;
        LottoTickets userLottoTickets = buyLottoTickets(count);

        LottoTicket wn = getWinningNumbers();
        Result result = MatchLottoTicket.matchLotto(userLottoTickets, wn);
        printResult(result, money);
    }


    private static int getMoney() {
        int inputMoney = iv.getMoneyInput();
        rv.printExchange((inputMoney / LOTTO_PRICE), inputMoney % LOTTO_PRICE);
        return (inputMoney / LOTTO_PRICE) * LOTTO_PRICE;
    }

    private static LottoTickets buyLottoTickets(int count) {
        LottoTickets lt = getManualAndAutoLotto(count);
        rv.printCountOfLottoTickets(lt.getTicket().size());
        rv.printLottoTickets(lt);
        return lt;
    }


    private static LottoTickets getManualAndAutoLotto(int count) {
        ArrayList<String> manualLotto = getManualLotto(count);
        List<LottoTicket> tickets = manualLotto.stream()
                .map(lotto -> new LottoTicket(lotto, ""))
                .collect(Collectors.toList());
        for (int i = 0; i < count; i++) {
            tickets.add(LottoTicketGenerator.generate());
        }
        return new LottoTickets(tickets);
    }

    private static ArrayList<String> getManualLotto(int count) {
        int manualCount = iv.getManualCount(count);
        validateManualCount(manualCount, count);
        ArrayList<String> manualLotto = iv.getManualLottoNumbers(manualCount);
        try {
            manualLotto.stream().map(Integer::parseInt);
        } catch (NumberFormatException e) {
            System.out.println("숫자와 구분자만 입력 가능 합니다.");
        }
        return manualLotto;
    }

    private static LottoTicket getWinningNumbers() {
        return createWinningNumbersInstance(iv.getWinningNumbers(), iv.getBonusNumber());
    }


    private static void validateManualCount(int manualCount, int count) {
        if (manualCount < 0) {
            throw new IllegalArgumentException("0 이상의 값만 입력 가능합니다.");
        }
        if (count < manualCount) {
            throw new IllegalArgumentException("구매 가능한 범위 내의 값만 입력 가능합니다.");
        }
    }

    private static LottoTicket createWinningNumbersInstance(String winningNumbers, String bonusNumber) {
        return new LottoTicket(winningNumbers, bonusNumber);
    }

    private static void printResult(Result result, int money) {
        rv.printResultStatistics(result);
        rv.printRevenueRate(result, money);
    }
}
