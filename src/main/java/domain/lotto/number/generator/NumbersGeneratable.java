package domain.lotto.number.generator;

import java.util.List;

@FunctionalInterface
public interface NumbersGeneratable {
    List<Integer> generate();
}
