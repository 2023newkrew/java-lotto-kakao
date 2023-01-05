package view;

import buyer.BuyerResult;
import lotto.Lotto;
import lotto.Rank;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public void printQuantity(int manualQuantity, int autoQuantity) {
        System.out.println(
                "수동으로 " +
                Integer.toString(manualQuantity) +
                "장, 자동으로 " +
                Integer.toString(autoQuantity) +
                "개를 구매했습니다."
        );
    }

    public void printLotteries(List<Lotto> lotteries) {
        lotteries.forEach(this::printLottery);
    }

    private void printLottery(Lotto lottery) {
        System.out.println(
                lottery.getLottoNumbers()
                        .stream()
                        .map(lotteryNumber -> Integer.toString(lotteryNumber.getNumber()))
                        .collect(Collectors.joining(", "))
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
        printProfit(result);
    }

    private void printRankInfo(Rank rank) {
        System.out.print(rank.matchCount + "개 일치");
        System.out.print("(" + rank.prize + ")- ");
    }

    public void printProfit(BuyerResult result) {
        System.out.println("총 수익률은 " + result.getProfit() + "입니다");
    }
}
