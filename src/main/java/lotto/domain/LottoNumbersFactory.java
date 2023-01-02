package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.generatepolicy.DefaultGeneratePolicy;
import lotto.generatepolicy.GeneratePolicy;

public class LottoNumbersFactory {

    private static final GeneratePolicy defaultGeneratePolicy = new DefaultGeneratePolicy();

    public static LottoNumbers create() {
        return create(defaultGeneratePolicy);
    }

    public static LottoNumbers create(GeneratePolicy generatePolicy) {
        List<Integer> numbers = new ArrayList<>();
        while (numbers.size() != 6) {
            addNumbers(numbers, generatePolicy.generate());
        }
        return new LottoNumbers(numbers);
    }

    private static void addNumbers(List<Integer> numbers, int target) {
        if (!numbers.contains(target)) {
            numbers.add(target);
        }
    }
}
