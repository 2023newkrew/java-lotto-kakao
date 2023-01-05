package lotto.strategy;

import java.util.List;

@FunctionalInterface
public interface AutoNumberSelectStrategy {
    List<Integer> selectNumbers();
}
