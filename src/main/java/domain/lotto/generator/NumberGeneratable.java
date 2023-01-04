package domain.lotto.generator;

import java.util.List;

@FunctionalInterface
public interface NumberGeneratable {
    List<Integer> generate();
}
