package lotto.views;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import lotto.models.Lottery;
import lotto.models.LotteryEarningsRate;
import lotto.models.enums.Rank;
import lotto.models.LotteryStatistics;

public class OutputView {
    Console console;

    public OutputView(Console console) {
        this.console = console;
    }

    public void askForMoneyToBuyLottery() {
        console.printOutput("구입금액을 입력해 주세요.");
    }

    public void printNumberOfLottery(Integer numberOfLottery) {
        console.printOutput(numberOfLottery.toString() + "개를 구매했습니다.");
    }

    public void printLotteries(List<Lottery> lotteries) {
        for (Lottery lottery :
                lotteries) {
            console.printOutput(lottery.getNumbersString());
        }
    }

    public void askForLastGoalNumbers() {
        console.printOutput("지난 주 당첨 번호를 입력해 주세요.");
    }

    public void askForBonusBall() {
        console.printOutput("보너스 볼을 입력해 주세요.");
    }

    public void printStatistics(LotteryStatistics statistics) {
        console.printOutput("당첨 통계\n"
                + "----------\n"
                + "3개 일치 (" + Rank.FIFTH.getPrize() + "원)- " + statistics.getCountOf(Rank.FIFTH) + "개\n"
                + "4개 일치 (" + Rank.FOURTH.getPrize() + "원)- " + statistics.getCountOf(Rank.FOURTH) + "개\n"
                + "5개 일치 (" + Rank.THIRD.getPrize() + "원)- " + statistics.getCountOf(Rank.THIRD) + "개\n"
                + "5개 일치, 보너스 볼 일치(" + Rank.SECOND.getPrize() + "원) - " + statistics.getCountOf(
                Rank.SECOND) + "개\n"
                + "6개 일치 (" + Rank.FIRST.getPrize() + "원)- " + statistics.getCountOf(Rank.FIRST) + "개\n");
    }

    public void printEarningsRate(LotteryEarningsRate lotteryEarningsRate) {
        console.printOutput("총 수익률은 " + lotteryEarningsRate.getStringUpToTwoDecimalPlaces() + "입니다.\n");
    }

    public <T> T requestUntilSuccess(Supplier<T> getT) {
        Optional<T> result = wrapTryCatch(getT);
        while (result.isEmpty()) {
            result = wrapTryCatch(getT);
        }
        return result.get();
    }

    private <T> Optional<T> wrapTryCatch(Supplier<T> getT) {
        try {
            return Optional.of(getT.get());
        } catch (RuntimeException e) {
            console.printError(e.getMessage());
            return Optional.empty();
        }
    }
}