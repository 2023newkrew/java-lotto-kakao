package lotto.factory;

import static lotto.domain.LottoConstants.LOTTO_NUMBER_COUNT;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.LottoNumbers;
import lotto.generatepolicy.GeneratePolicy;

public class LottoNumbersFactory {
    public static LottoNumbers create(GeneratePolicy generatePolicy) {
        List<Integer> numbers = new ArrayList<>();
        while (isNotFull(numbers)) {
            addNumbers(numbers, generatePolicy.generate());
        }
        numbers.sort(Integer::compare);
        return new LottoNumbers(numbers);
    }

    private static boolean isNotFull(List<Integer> numbers) {
        return numbers.size() != LOTTO_NUMBER_COUNT;
    }

    private static void addNumbers(List<Integer> numbers, int target) {
        if (!numbers.contains(target)) {
            numbers.add(target);
        }
    }
}
