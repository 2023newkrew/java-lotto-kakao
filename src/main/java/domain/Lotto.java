package domain;

import common.constant.Constants;
import common.state.Result;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Lotto {

    public static final int START_INDEX = 0;

    private final List<Integer> numbers;

    public Lotto() {
        List<Integer> WHOLE_NUMBERS = IntStream.rangeClosed(Constants.MINIMUM, Constants.MAXIMUM)
            .boxed()
            .collect(Collectors.toList());
        Collections.shuffle(WHOLE_NUMBERS);
        List<Integer> numbersIn = WHOLE_NUMBERS.subList(START_INDEX, START_INDEX + Constants.LENGTH);
        Collections.sort(numbersIn);
        this.numbers = Collections.unmodifiableList(numbersIn);
    }

    public Lotto(String input) {
        this.numbers = Arrays.stream(input.split(Constants.DELIMITER))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public Result getResult(WinningLotto winningLotto, BonusNumber bonusNumber) {
        List<Integer> winningLottoNumbers = winningLotto.getWinningLottoNumbers();
        int count = (int)numbers.stream()
                .filter(winningLottoNumbers::contains)
                .count();
        if (count == 5 && isBonus(bonusNumber)) {
            return Result.FIVEBONUS;
        }
        return Result.of(count);
    }

    public boolean isBonus(BonusNumber bonusNumber) {
        return numbers.contains(bonusNumber.number);
    }

    public String getLottoNumbers() {
        return numbers.toString();
    }
}
