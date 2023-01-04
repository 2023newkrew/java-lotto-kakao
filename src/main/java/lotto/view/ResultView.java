package lotto.view;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;

import java.util.List;

import static lotto.utils.Constants.MIN_PURCHASE_PRICE;
import static lotto.utils.LottoMessage.*;

public class ResultView {

    public void printPurchaseCount(int amount){
        System.out.println(amount/MIN_PURCHASE_PRICE + RESULT_PURCHASE_COUNT.getMessage());
    }

    public void printWinningStatics(List<Integer> result, double rate){
        System.out.println(RESULT_WINNING_STAT.getMessage());
        System.out.println(RESULT_LINE.getMessage());
        System.out.println(RESULT_THREE_MATCHING.getMessage() + result.get(0));
        System.out.println(RESULT_FOUR_MATCHING.getMessage() + result.get(1));
        System.out.println(RESULT_FIVE_MATCHING.getMessage() + result.get(2));
        System.out.println(RESULT_FIVE_BONUS_MATCHING.getMessage() + result.get(4));
        System.out.println(RESULT_SIX_MATCHING.getMessage() + result.get(3));
        printRateOfReturn(rate);
    }

    private void printRateOfReturn(double rate){
        System.out.printf("총 수익률은 %.2f 입니다.", rate);
        if(rate < 1.0) {
            System.out.println(RESULT_BAD.getMessage());
            return;
        }
        System.out.println(RESULT_GOOD.getMessage());
    }

    public void printLottoTickets(LottoTickets lottoTickets) {
        List<LottoTicket> tickets = lottoTickets.getTickets();
        for(LottoTicket ticket : tickets){
            System.out.println(ticket.getLottoNumbers().toString());
        }
    }
}
