package lotto.exception;

import static lotto.domain.LottoConstants.*;
import static lotto.domain.LottoConstants.MAX_LOTTO_NUMBER;

public final class ExceptionMessage {

    public static final String LOTTO_NUMBER_BOUND_EXCEPTION_MESSAGE = String.format("LottoNumber는 %d ~ %d 값을 가집니다.",
            MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER);
    public static final String SIZE_EXCEPTION_MESSAGE = String.format("LottoNumbers의 LottoNumber 사이지는 %d여야 합니다.",
            LOTTO_SIZE);
    public static final String BONUS_BALL_DUPLICATE_EXCEPTION_MESSAGE = "보너스 볼이 다른 볼과 중복입니다.";

    public static final String INVALID_MATCH_COUNT_AND_HAS_BONUS = "유효하지않은 matchCount와 hasBonus입니다.";

}
