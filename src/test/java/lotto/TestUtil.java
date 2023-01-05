package lotto;

import lotto.model.ticket.SingleLottoNumber;

import java.util.Set;
import java.util.stream.Collectors;

public class TestUtil {

    public static Set<SingleLottoNumber> toSingleLottoNumbers(Set<Integer> numbers) {
        return numbers.stream()
                .map(SingleLottoNumber::valueOf)
                .collect(Collectors.toSet());
    }
}
