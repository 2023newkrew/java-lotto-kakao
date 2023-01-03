package lotto.model;

import java.util.List;

public interface LottosGenerator {

    List<Lotto> generate(long count);
}
