package lotto;

import java.util.List;

@FunctionalInterface
public interface NumberSelectStrategy {
    List<Integer> select();
}
