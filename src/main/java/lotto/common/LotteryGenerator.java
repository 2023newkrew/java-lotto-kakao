package lotto.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.models.Lottery;

public class LotteryGenerator {

    static public final Integer MIN_VALUE = 1;

    static public final Integer MAX_VALUE = 45;

    static public final Integer LOTTERY_COUNT = 6;

    static private final List<Integer> lotteryPreset = IntStream
            .rangeClosed(MIN_VALUE, MAX_VALUE)
            .boxed()
            .collect(Collectors.toList());

    public static Lottery createLotteryAuto() {
        Collections.shuffle(lotteryPreset);
        List<Integer> numbers = lotteryPreset.subList(0, LOTTERY_COUNT).stream()
                .sorted()
                .collect(Collectors.toList());
        return new Lottery(numbers);
    }

    public static Lottery createLotteryManual(List<Integer> numbers) {
        return new Lottery(numbers);
    }

    public static List<Lottery> createLotteries(int numberOfLottery) {
        List<Lottery> list = new ArrayList<>();
        for (int i = 0; i < numberOfLottery; i++) {
            list.add(createLotteryAuto());
        }
        return list;
    }
}