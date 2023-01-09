package lotto.utils;

import lotto.constant.Constants;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 유저 인풋에 대한 validation을 판별하는 유틸 클래스입니다.
 */
public class UserInputValidator {

    public static void checkNullOrEmpty(String input) {
        if (Objects.isNull(input) || input.isEmpty()) {
            throw new IllegalArgumentException("null값을 입력하지 마세요.");
        }
    }

    public static int stringToInteger(String input) {
        int number;
        try {
            number = Integer.parseInt(input);
        } catch (Exception e) {
            throw new IllegalArgumentException("숫자로 입력해야 합니다.");
        }
        return number;
    }

    public static void checkUnderPriceOfLotto(int money) {
        if (money < Constants.PRICE_OF_LOTTO) {
            throw new IllegalArgumentException("1장에 1000원이므로 1000원 이상을 입력해주세요.");
        }
    }

    public static List<Integer> stringToIntgerList(String input) {
        List<Integer> numbers;
        try {
            numbers = Arrays.stream(removeSpaceAndSplit(input))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException("숫자로 입력해야 합니다.");
        }
        return numbers;
    }

    private static String[] removeSpaceAndSplit(String input) {
        return input.replaceAll(Constants.SPACES, Constants.EMPTY_STRING).split(Constants.COMMA);

    }

    public static void checkSize(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != Constants.LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개를 입력해야 합니다.");
        }
    }

    public static void checkDuplicate(List<Integer> lottoNumbers) {
        Set<Integer> splitedInputSet = new HashSet<>(lottoNumbers);
        if (splitedInputSet.size() < lottoNumbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복 입력 해서는 안됩니다.");
        }
    }

    public static void checkLottoNumbersInRange(List<Integer> lottoNumbers) {
        for (int lottoNumber : lottoNumbers) {
            checkInRange(lottoNumber);
        }
    }

    public static void checkInRange(int number) {
        if (number < Constants.LOTTO_MIN_NUM || number > Constants.LOTTO_MAX_NUM) {
            throw new IllegalArgumentException("로또 번호는 1~45 사이입니다.");
        }
    }

    public static void checkDuplicateLottoNumberWithBonusNumber(List<Integer> lottoNumbers, int bonusNumber) {
        if (lottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 볼은 로또 번호와 중복되면 안됩니다.");
        }
    }

    public static void checkNegative(int numberOfManualLotto) {
        if (numberOfManualLotto < 0) {
            throw new IllegalArgumentException("음수는 입력이 불가능합니다.");
        }
    }

    public static void checkOverBudget(int numberOfManualLotto, int money) {
        if (money < numberOfManualLotto * Constants.PRICE_OF_LOTTO) {
            throw new IllegalArgumentException("예산을 넘는 수동복권은 구매가 불가능합니다.");
        }
    }
}
