package lotto.exception;

import static lotto.domain.LottoConstants.LOTTO_NUMBER_COUNT;
import static lotto.domain.LottoConstants.LOTTO_NUMBER_LOWER_BOUND;
import static lotto.domain.LottoConstants.LOTTO_NUMBER_UPPER_BOUND;

public final class ExceptionMessage {
    public static final String OUT_OF_BOUNDS_EXCEPTION_MESSAGE = String.format("%d ~ %d 값이 주어져야 합니다.",
            LOTTO_NUMBER_LOWER_BOUND.getValue(), LOTTO_NUMBER_UPPER_BOUND.getValue());
    public static final String NOT_UNIQUE_EXCEPTION_MESSAGE = "중복된 값이 주어질수 없습니다.";
    public static final String INVALID_COUNT_EXCEPTION_MESSAGE = String.format("로또는 %d개의 숫자로 이루어져야 합니다.",
            LOTTO_NUMBER_COUNT.getValue());

    public static final String DUPLICATE_BONUS_NUMBER_EXCEPTION_MESSAGE = "보너스 넘버는 기존 넘버와 중복될 수 업습니다.";
    public static final String INVALID_LOTTO_RESULT_INPUT_EXCEPTION = "Lotto result에 잘못된 입력이 주어졌습니다.";

}
