package view;

import domain.buyer.Buyer;
import domain.buyer.BuyerProfit;
import domain.buyer.BuyerResult;
import domain.lotto.LotteryDTO;
import domain.lotto.Rank;

import java.util.Arrays;
import java.util.List;

public class OutputView {
    public void printLotteriesInfo(Buyer buyer) {
        System.out.print("수동으로 " + buyer.getManualCount() + "장, ");
        System.out.println("자동으로 " + buyer.getAutoCount() + "장을 구매했습니다.");
    }

    public void printLotteries(List<LotteryDTO> lotteries) {
        lotteries.forEach(this::printLottery);
        System.out.println();
    }

    private void printLottery(LotteryDTO lottery) {
        System.out.println(lottery);
    }

    public void printResult(BuyerResult result) {
        System.out.println("당첨 통계");
        System.out.println("-------");
        Arrays.stream(Rank.values()).forEach((e) -> {
            if (e != Rank.NONE) {
                printRankInfo(e);
                System.out.println(result.getRankCount(e) + "개");
            }
        });
    }

    private void printRankInfo(Rank rank) {
        System.out.print(rank.match.getMatchCount() + "개 일치");
        if (rank.equals(Rank.SECOND))
            System.out.print(", 보너스볼 일치");
        System.out.print("(" + rank.prize + ")- ");
    }

    public void printProfit(BuyerProfit buyerProfit) {
        System.out.println("총 수익률은 " + buyerProfit.getProfit() + "입니다");
    }
}
