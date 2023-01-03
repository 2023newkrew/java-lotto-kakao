package domain;

import common.constant.Constants;
import common.state.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Lotto {

    public static final int START_INDEX = 0;
    public static final List<Integer> WHOLE_NUMBERS = IntStream.rangeClosed(Constants.MINIMUM, Constants.MAXIMUM)
            .boxed()
            .collect(Collectors.toList());

    private final List<Integer> numbers;

    public Lotto() {
        Collections.shuffle(WHOLE_NUMBERS);
        List<Integer> numbers = WHOLE_NUMBERS.subList(START_INDEX, START_INDEX + Constants.LENGTH);
        Collections.sort(numbers);
        this.numbers = Collections.unmodifiableList(numbers);
    }

    public Lotto(String input) {
        this.numbers = Arrays.stream(input.split(Constants.DELIMITER))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    // 꼭 고치겠습니다 :(
    public Result getResult(WinningLotto winningLotto, BonusNumber bonusNumber) {
        List<Integer> winningLottoNumbers = winningLotto.getWinningLottoNumbers();
        int count = (int)numbers.stream()
                .filter(number -> winningLottoNumbers.contains(number))
                .count();

        if (count == 3) {
            return Result.THREE;
        }
        if (count == 4) {
            return Result.FOUR;
        }
        if (count == 5 && isBonus(bonusNumber)) {
            return Result.FIVEBONUS;
        }
        if (count == 5) {
            return Result.FIVE;
        }
        if (count == 6) {
            return Result.SIX;
        }
        return Result.NONE;
    }

    public boolean isBonus(BonusNumber bonusNumber) {
        return numbers.contains(bonusNumber.number);
    }
}
