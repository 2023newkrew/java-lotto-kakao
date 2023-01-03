package domain.lotto.makingnumbers;

import java.util.List;

@FunctionalInterface
public interface NumberGeneratable {
    List<Integer> generate();
}
