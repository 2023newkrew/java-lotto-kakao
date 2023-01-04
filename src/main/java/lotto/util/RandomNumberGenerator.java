package lotto.util;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class RandomNumberGenerator {
    public static List<Integer> getRandomNumbers(int n, int start, int end) {
        if(end - start + 1 < n) throw new RuntimeException("숫자 생성 범위가 이상합니다.");
        return ThreadLocalRandom
                .current()
                .ints(start, end)
                .distinct()
                .limit(n)
                .boxed()
                .collect(Collectors.toList());
    }
}
