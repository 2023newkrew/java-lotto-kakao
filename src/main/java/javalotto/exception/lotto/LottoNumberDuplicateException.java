package javalotto.exception.lotto;

import java.util.List;

public class LottoNumberDuplicateException extends IllegalArgumentException {
    public LottoNumberDuplicateException(List<Integer> numbers) {
        this(String.format("로또 번호는 중복 될 수 없습니다. 주어진 로또 번호 %s", numbers));
    }

    public LottoNumberDuplicateException(String message) {
        super(message);
    }
}
