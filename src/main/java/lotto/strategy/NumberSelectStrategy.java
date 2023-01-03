package lotto.strategy;

import java.util.List;

@FunctionalInterface
public interface NumberSelectStrategy {
    List<Integer> selectNumbers();
}
