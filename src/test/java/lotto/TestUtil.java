package lotto;

import lotto.model.vo.LottoNumber;

import java.util.List;
import java.util.stream.Collectors;

public class TestUtil {

    public static List<LottoNumber> toLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
    }
}
