package lotto.exception;

import static lotto.domain.LottoConstants.LOTTO_NUMBER_COUNT;
import static lotto.domain.LottoConstants.LOTTO_NUMBER_LOWER_BOUND;
import static lotto.domain.LottoConstants.LOTTO_NUMBER_UPPER_BOUND;

import lotto.domain.LottoConstants;

public final class ExceptionMessages {
    public static final String OUT_OF_BOUNDS_EXCEPTION_MESSAGE = String.format("%d ~ %d 값이 주어져야 합니다.",
            LOTTO_NUMBER_LOWER_BOUND, LOTTO_NUMBER_UPPER_BOUND);
    public static final String NOT_UNIQUE_EXCEPTION_MESSAGE = "중복된 값이 주어질수 없습니다.";
    public static final String INVALID_COUNT_EXCEPTION_MESSAGE = String.format("로또는 %d개의 숫자로 이루어져야 합니다.",
            LOTTO_NUMBER_COUNT);

}
