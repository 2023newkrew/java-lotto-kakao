package lotto.view;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.utils.LottoRank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static lotto.utils.LottoMessage.*;
import static lotto.utils.LottoRank.*;

public class ResultView {

    public void printPurchaseCount(int amount, int manual){
        System.out.println(amount + RESULT_PURCHASE_COUNT.getMessage());
        System.out.printf("수동으로 %d장, 자동으로 %d%s\n", manual, amount - manual, RESULT_PURCHASE_COUNT);
    }

    public void printWinningStatics(Map<LottoRank, Integer> result, double rate){
        System.out.println(RESULT_WINNING_STAT.getMessage());
        System.out.println(RESULT_LINE.getMessage());
        System.out.println(RESULT_THREE_MATCHING.getMessage() + resultCount(result, FIFTH));
        System.out.println(RESULT_FOUR_MATCHING.getMessage() + resultCount(result, FOURTH));
        System.out.println(RESULT_FIVE_MATCHING.getMessage() + resultCount(result, THIRD));
        System.out.println(RESULT_FIVE_BONUS_MATCHING.getMessage() + resultCount(result, SECOND));
        System.out.println(RESULT_SIX_MATCHING.getMessage() + resultCount(result, FIRST));
        printRateOfReturn(rate);
    }

    // 각 등수 당 몇 개씩 당첨 되었는지
    private int resultCount(Map<LottoRank, Integer> result, LottoRank rank){
        Integer count = result.get(rank);
        if(count == null) count = 0;
        return count;
    }

    private void printRateOfReturn(double rate){
        System.out.printf("총 수익률은 %.2f 입니다.", rate);
        if(rate < 1.0) {
            System.out.println(RESULT_BAD.getMessage());
            return;
        }
        System.out.println(RESULT_GOOD.getMessage());
    }
    public void printLottoTickets(LottoTicket lottoTicket) {
        ArrayList<LottoNumber> tickets = lottoTicket.getTicket();
        for(LottoNumber ticket : tickets){
            printEachTicket(ticket.getLottoNumbers());
        }
    }

    private void printEachTicket(List<Integer> numbers){
        System.out.printf("[%d, %d, %d, %d, %d, %d]\n",
                numbers.get(0), numbers.get(1),
                numbers.get(2), numbers.get(3),
                numbers.get(4) ,numbers.get(5)
        );
    }
}
