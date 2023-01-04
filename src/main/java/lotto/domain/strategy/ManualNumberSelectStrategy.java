package lotto.domain.strategy;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class ManualNumberSelectStrategy implements  NumberSelectStrategy {

    private final Queue<List<Integer>> queue;

    public ManualNumberSelectStrategy(List<List<Integer>> numberListList) {
        queue = new ArrayDeque<>(numberListList);
    }

    @Override
    public List<Integer> select() {
        return queue.remove();
    }
}
