package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lotterynumber.LotteryNumberCombination;
import lotto.domain.lotterynumber.RandomLotteryNumberCombinationFactory;

public class LotteryVendingMachine {
    public static List<LotteryNumberCombination> sellSelfPicksWith(List<List<Integer>> selfPickNumbers) {
        return selfPickNumbers.stream()
                .map(LotteryNumberCombination::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public static List<LotteryNumberCombination> sellQuickPicks(int quickPickCount) {
        return RandomLotteryNumberCombinationFactory.createMany(quickPickCount);
    }
}
