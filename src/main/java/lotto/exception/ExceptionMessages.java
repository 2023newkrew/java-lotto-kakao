package lotto.exception;

import static lotto.domain.LottoConstants.LOTTO_NUMBERS_INPUT_DELIMITER;
import static lotto.domain.LottoConstants.LOTTO_NUMBERS_LENGTH;
import static lotto.domain.LottoConstants.LOTTO_NUMBER_LOWER_BOUND;
import static lotto.domain.LottoConstants.LOTTO_NUMBER_UPPER_BOUND;
import static lotto.domain.LottoConstants.LOTTO_UNIT_PRICE;

public final class ExceptionMessages {
    public static final String OUT_OF_BOUNDS_EXCEPTION_MESSAGE = String.format("[ERROR] %d ~ %d 값이 주어져야 합니다.",
            LOTTO_NUMBER_LOWER_BOUND, LOTTO_NUMBER_UPPER_BOUND);
    public static final String NOT_UNIQUE_EXCEPTION_MESSAGE = "[ERROR] 중복된 값이 주어질수 없습니다.";
    public static final String INVALID_COUNT_EXCEPTION_MESSAGE = String.format("[ERROR] 로또는 %d개의 숫자로 이루어져야 합니다.",
            LOTTO_NUMBERS_LENGTH);

    public static final String DUPLICATE_BONUS_NUMBER_EXCEPTION_MESSAGE = "[ERROR] 보너스 넘버는 기존 넘버와 중복될 수 업습니다.";
    public static final String INVALID_LOTTO_RESULT_INPUT_EXCEPTION = "[ERROR] Lotto Result에 잘못된 입력이 주어졌습니다.";
    public static final String LOTTO_ANSWER_NUMBERS_TYPE_EXCEPTION_MESSAGE =
            String.format("[ERROR] 로또 번호는 %s로 구분되는 정수여야 합니다.", LOTTO_NUMBERS_INPUT_DELIMITER);
    public static final String PRICE_TYPE_EXCEPTION_MESSAGE = "[ERROR] 가격은 정수여야 합니다.";
    public static final String BONUS_BALL_TYPE_EXCEPTION_MESSAGE = "[ERROR] 보너스 볼은 정수여야 합니다.";
    public static final String NON_EXISTENT_PRICE_EXCEPTION_MESSAGE = "[ERROR] 구입 금액은 양수여야 합니다.";
    public static final String NOT_DIVISIBLE_PRICE_EXCEPTION_MESSAGE = String.format(
            "[ERROR] 나누어떨어지지 않는 금액입니다. 다시 입력해주세요. (개당 가격 : %d 원)", LOTTO_UNIT_PRICE);
    public static final String INCOMPLETE_RANK_EXCEPTION_MESSAGE = "[ERROR] 해당하는 등수가 없습니다.";

}
