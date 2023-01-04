package javalotto.domain;

import java.util.List;

public interface NumberGenerator {
    List<Integer> generateNumbers(int minInclusive, int maxExclusive, int count);
}
