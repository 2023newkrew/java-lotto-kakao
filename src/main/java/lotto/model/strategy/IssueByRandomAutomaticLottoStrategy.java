package lotto.model.strategy;

import lotto.model.Lotto;
import lotto.model.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class IssueByRandomAutomaticLottoStrategy implements LottoIssueStrategy {

    private final List<Integer> numberPool = new ArrayList<>();

    {
        IntStream.range(LottoNumber.MIN_RANGE, LottoNumber.MAX_RANGE)
                .forEach(numberPool::add);
    }

    @Override
    public Lotto issue() {
        Collections.shuffle(numberPool);
        List<Integer> numbers = new ArrayList<>(numberPool.subList(0, Lotto.NUMBER_LENGTH));
        Collections.sort(numbers);
        return Lotto.of(numbers);
    }
}
