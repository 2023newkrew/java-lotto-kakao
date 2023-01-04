package lotto.util;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomNumberGenerator {
    private static Random random = new Random();

    public static List<Integer> getRandomNumbers(int n, int start, int end) {
        if(end - start + 1 < n) throw new RuntimeException();
        return random.ints(start, end + 1).distinct().limit(n).boxed().collect(Collectors.toList());
    }
}
