package lotto.exception;

import static lotto.domain.LottoConstants.*;
import static lotto.domain.LottoConstants.MAX_LOTTO_NUMBER;

import lotto.domain.LottoConstants;

public final class ExceptionMessage {

    public static final String LOTTO_NUMBER_BOUND_EXCEPTION_MESSAGE = String.format("LottoNumber는 %d ~ %d 값을 가집니다.",
            MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER);
}
