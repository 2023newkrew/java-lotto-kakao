package javalotto.exception.lotto;

import java.util.List;

import static javalotto.domain.Lotto.LOTTO_NUMBER_MAX_VALUE;
import static javalotto.domain.Lotto.LOTTO_NUMBER_MIN_VALUE;

public class LottoNumberOutOfRangeException extends IllegalArgumentException {

    public LottoNumberOutOfRangeException(List<Integer> numbers) {
        this(String.format("로또 번호는 %d 이상 %d 이하여야 합니다. 주어진 로또 번호: %s",
                LOTTO_NUMBER_MIN_VALUE, LOTTO_NUMBER_MAX_VALUE, numbers));
    }

    public LottoNumberOutOfRangeException(String message) {
        super(message);
    }
}
