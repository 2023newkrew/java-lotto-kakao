package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.LottoGradeEnum;
import lotto.dto.LottoGradeCountResult;

public class LottoGradeCounter {

    private final Map<LottoGradeEnum, Integer> counter;
    private static final int FIRST = 1;
    private static final int FIFTH = 5;
    public LottoGradeCounter() {
        counter = IntStream.range(FIRST, FIFTH + 1)
                .boxed()
                .collect(Collectors.toMap(LottoGradeEnum::getGrade, i -> 0));
    }

    public void putGrade(LottoGradeEnum grade) {
        if (grade == LottoGradeEnum.NONE_GRADE) {
            return;
        }
        counter.put(grade, counter.get(grade) + 1);
    }

    public int getTotalPrice() {
        return counter.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public List<LottoGradeCountResult> getLottoGradeCountResults() {
        return counter.entrySet()
                .stream()
                .map(entry -> new LottoGradeCountResult(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public void clear() {
        counter.clear();
    }
}
