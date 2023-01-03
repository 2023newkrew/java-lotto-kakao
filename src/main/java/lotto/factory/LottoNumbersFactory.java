package lotto.factory;

import static lotto.domain.LottoConstants.LOTTO_NUMBER_COUNT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.domain.LottoNumbers;
import lotto.generatepolicy.GeneratePolicy;

public class LottoNumbersFactory {
    public static LottoNumbers create(GeneratePolicy generatePolicy) {
        Set<Integer> numbers = new HashSet<>();
        while(isNotFull(numbers)){
            addNumbers(numbers, generatePolicy.generate());
        }
        List<Integer> result = new ArrayList<>(numbers);
        Collections.sort(result);
        return new LottoNumbers(result);
    }

    private static boolean isNotFull(Set<Integer> numbers) {
        return numbers.size() != LOTTO_NUMBER_COUNT.getValue();
    }

    private static void addNumbers(Set<Integer> numbers, int target) {
        numbers.add(target);
    }
}
