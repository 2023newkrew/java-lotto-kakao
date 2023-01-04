package view;

import buyer.BuyerProfit;
import buyer.BuyerResult;
import lotto.Lotto;
import lotto.Rank;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public void printLotteries(List<Lotto> lotteries) {
        lotteries.forEach(this::printLottery);
    }

    private void printLottery(Lotto lottery) {
        System.out.println(
                lottery.getLottoNumbers()
                        .stream()
                        .map(lotteryNumber -> Integer.toString(lotteryNumber.getNumber()))
                        .collect(Collectors.joining(","))
        );
    }

    public void printResult(BuyerResult result) {
        Arrays.stream(Rank.values()).forEach((e) -> {
            if (e != Rank.NONE) {
                printRankInfo(e);
                System.out.println(
                        (result.getResult().getOrDefault(e, 0)) + "개");
            }
        });
    }

    private void printRankInfo(Rank rank) {
        System.out.print(rank.match.getMatchCount() + "개 일치");
        System.out.print("(" + rank.prize + ")- ");
    }

    public void printProfit(BuyerProfit buyerProfit) {
        System.out.println("총 수익률은 " + buyerProfit.getProfit() + "입니다");
    }
}
