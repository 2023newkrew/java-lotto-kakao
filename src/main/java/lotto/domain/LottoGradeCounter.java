package lotto.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lotto.LottoGradeEnum;
import lotto.dto.LottoGradeCountResult;

public class LottoGradeCounter {

    private final Map<LottoGradeEnum, Integer> counter;

    public LottoGradeCounter() {
        counter = Arrays.stream(LottoGradeEnum.values())
                .filter((grade) -> grade != LottoGradeEnum.NONE_GRADE)
                .collect(Collectors.toMap(Function.identity(), grade -> 0));
    }

    public void putGrade(LottoGradeEnum grade) {
        if (grade == LottoGradeEnum.NONE_GRADE) {
            return;
        }
        counter.put(grade, counter.get(grade) + 1);
    }

    public long getTotalPrice() {
        return counter.entrySet()
                .stream()
                .mapToLong(entry -> (long) entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public List<LottoGradeCountResult> getLottoGradeCountResults() {
        return counter.entrySet()
                .stream()
                .map(entry -> new LottoGradeCountResult(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(result -> result.getGrade().getPrice()))
                .collect(Collectors.toList());
    }

    public void clear() {
        counter.clear();
    }
}
