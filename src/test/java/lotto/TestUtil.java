package lotto;

import lotto.model.prize.Prize;
import lotto.model.vo.LottoNumber;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestUtil {

    public static List<LottoNumber> toLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
    }

    public static List<Prize> repeatPrizes(Prize prize, int count) {
        return IntStream.range(0, count)
                .mapToObj(ignore -> prize)
                .collect(Collectors.toList());
    }
}
