package lotto.view;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;

import java.util.ArrayList;
import java.util.List;

import static lotto.utils.LottoMessage.*;

public class ResultView {

    public void printPurchaseCount(int amount){
        System.out.println(amount + RESULT_PURCHASE_COUNT.getMessage());
    }

    public void printWinningStatics(ArrayList<Integer> result, double rate){
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
    public void printLottoTickets(LottoTicket lottoTicket) {
        ArrayList<LottoNumber> tickets = lottoTicket.getTickets();
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
