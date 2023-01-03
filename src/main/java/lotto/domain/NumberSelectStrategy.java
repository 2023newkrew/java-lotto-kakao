package lotto.domain;

import java.util.List;

@FunctionalInterface
public interface NumberSelectStrategy {
    List<Integer> select();
}
