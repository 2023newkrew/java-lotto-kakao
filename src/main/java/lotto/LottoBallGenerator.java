package lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoBallGenerator implements Generator<LottoBall> {
    private final List<Integer> candidates = Stream.iterate(1, v -> v + 1)
            .limit(45)
            .collect(Collectors.toList());
    private int index = 0;

    @Override
    public LottoBall generate() {
        int currentIndex = index;
        this.index = (this.index + 1) % 6;
        if (currentIndex % 6 == 0) {
            Collections.shuffle(candidates);
        }
        return new LottoBall(candidates.get(currentIndex));
    }
}
