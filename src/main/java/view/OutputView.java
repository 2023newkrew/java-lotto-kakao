package view;

import buyer.BuyerResult;
import lotto.Lottery;
import lotto.Rank;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public void printLotteries(List<Lottery> lotteries) {
        lotteries.forEach(this::printLottery);
    }

    private void printLottery(Lottery lottery) {
        System.out.println(
                lottery.getLotteryNumber()
                        .stream()
                        .map(Object::toString)
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
}
