package lotto.constant;

import static lotto.constant.LotteryConstant.LOTTERY_COUNT_MINIMUM;
import static lotto.constant.LotteryConstant.LOTTERY_NUMBERS_LENGTH;
import static lotto.constant.LotteryConstant.LOTTERY_NUMBER_MAXIMUM;
import static lotto.constant.LotteryConstant.LOTTERY_NUMBER_MINIMUM;
import static lotto.constant.LotteryConstant.LOTTERY_UNIT_PRICE;

public class ExceptionMessage {
    public static final String LOTTERY_NUMBER_OUT_OF_RANGE_FORMAT = "[ERROR] 로또 숫자의 범위는 %d 이상 %d 이하여야 합니다.";
    public static final String LOTTERY_NUMBER_OUT_OF_RANGE = String.format(LOTTERY_NUMBER_OUT_OF_RANGE_FORMAT,
            LOTTERY_NUMBER_MINIMUM, LOTTERY_NUMBER_MAXIMUM);
    public static final String LOTTERY_NUMBERS_WRONG_DISTINCT_COUNT_FORMAT = "[ERROR] 로또 숫자는 서로 다른 %d개의 숫자로 이루어져야 합니다.";
    public static final String LOTTERY_NUMBERS_WRONG_DISTINCT_COUNT = String.format(
            LOTTERY_NUMBERS_WRONG_DISTINCT_COUNT_FORMAT, LOTTERY_NUMBERS_LENGTH);
    public static final String BONUS_NUMBER_DUPLICATED = "[ERROR] 보너스 번호는 로또 숫자와 중복될 수 없습니다.";

    public static final String INPUT_SELF_PICK_COUNT_WRONG_TYPE = "[ERROR] 구매하는 개수는 정수여야 합니다.";
    public static final String INPUT_LOTTERY_NUMBER_WRONG_TYPE = "[ERROR] 로또 번호는 정수여야 합니다.";
    public static final String INPUT_BUDGET_WRONG_TYPE = "[ERROR] 구매 가격은 정수여야 합니다.";

    public static final String BUDGET_NOT_POSITIVE = "[ERROR] 구매 가격은 양수여야 합니다.";
    public static final String NOT_DIVISIBLE_BUDGET_FORMAT = "[ERROR] 구매 가격은 %d의 배수여야 합니다.";
    public static final String NOT_DIVISIBLE_BUDGET = String.format(NOT_DIVISIBLE_BUDGET_FORMAT, LOTTERY_UNIT_PRICE);
    public static final String NON_EXISTENT_SELF_PICK_COUNT_FORMAT = "[ERROR] 수동으로 구매하는 개수는 %d 이상이어야 합니다.";
    public static final String NON_EXISTENT_SELF_PICK_COUNT = String.format(NON_EXISTENT_SELF_PICK_COUNT_FORMAT,
            LOTTERY_COUNT_MINIMUM);
    public static final String SELF_PICK_COUNT_EXCEED_FORMAT = "[ERROR] 구매가능한 개수를 초과했습니다. 최대 개수 : %d";
    public static final String NON_EXISTENT_LOTTERY_COUNT_FORMAT = "[ERROR] 생성하는 로또는 %d개 이상이어야 합니다.";
    public static final String NON_EXISTENT_LOTTERY_COUNT = String.format(NON_EXISTENT_LOTTERY_COUNT_FORMAT,
            LOTTERY_COUNT_MINIMUM);
}
