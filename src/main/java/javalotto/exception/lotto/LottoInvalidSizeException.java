package javalotto.exception.lotto;

import java.util.List;

import static javalotto.domain.Lotto.LOTTO_NUMBERS_COUNT;

public class LottoInvalidSizeException extends IllegalArgumentException {
    public LottoInvalidSizeException(List<Integer> numbers) {
        this(String.format("로또 번호는 %d개여야 합니다. 주어진 로또 번호 개수: %d", LOTTO_NUMBERS_COUNT, numbers.size()));
    }

    public LottoInvalidSizeException(String message) {
        super(message);
    }
}
