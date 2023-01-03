package lotto;

import lotto.model.*;
import lotto.view.InputView;

import java.util.Scanner;

public class LottoMain {
    public static void main(String[] args) {
        InputView iv = InputView.getInstance();
        int money;
        money = iv.getMoneyInput();

        LottoTickets tickets = new LottoTickets.countOf(money);
        System.out.println(tickets.getTicket().size() + "개 구매했습니다.");
        for(LottoTicket ticket : tickets.getTicket()){
            System.out.println(ticket);
        }

        String winningNumbers,bonusNumber;
        winningNumbers = iv.getWinningNumbers();
        bonusNumber = iv.getBonusNumber();


        WinningNumbers wn = new WinningNumbers(winningNumbers, bonusNumber);

        Result result = tickets.getResults(wn);

        System.out.println("당첨 통계");
        System.out.println("--------");
        System.out.println("3개 일치 (5000원) - " + result.get(Grade.THREE) + "개");
        System.out.println("4개 일치 (50000원) - " + result.get(Grade.FOUR) + "개");
        System.out.println("5개 일치 (1500000원) - " + result.get(Grade.FIVE) + "개");
        System.out.println("5개 일치, 보너스 볼 일치(30000000원) - " + result.get(Grade.FIVE_BONUS) + "개");
        System.out.println("6개 일치 (2000000000원) - " + result.get(Grade.SIX) + "개");

        //수익률 출력
        float rate = (float) (
                result.get(Grade.THREE) * 5000 + result.get(Grade.FOUR) * 50000 + result.get(Grade.FIVE) * 1500000 + result.get(Grade.FIVE_BONUS) * 300000000 + result.get(Grade.SIX)
        ) / (float) money;
        System.out.printf("총 수익률은 %.2f 입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임", rate, rate > 1 ? "이익" : "손해");


    }
}
