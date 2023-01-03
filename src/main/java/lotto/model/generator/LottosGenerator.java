package lotto.model.generator;

import lotto.model.lotto.Lotto;

import java.util.List;

public interface LottosGenerator {

    List<Lotto> generate(long count);
}
