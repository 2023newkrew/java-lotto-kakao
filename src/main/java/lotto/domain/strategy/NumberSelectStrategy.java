package lotto.domain.strategy;

import java.util.List;

public interface NumberSelectStrategy {

    List<Integer> select();
    boolean isEnd();
}
