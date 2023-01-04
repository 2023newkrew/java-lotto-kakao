package domain.lotto.number;

import java.util.List;

@FunctionalInterface
public interface NumberGeneratable {
    List<Integer> generate();
}
