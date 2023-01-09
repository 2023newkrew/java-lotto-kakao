package lotto.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

class LottosTest {
    int money;
    Lottos lottos;
    final static int NUMBER_OF_LOTTOS = 10;
    final static List<Integer> DUMMY_LIST = Arrays.asList(1, 2, 3, 4, 5, 6);
    final static List<List<Integer>> DUMMY_MANUALLOTTO_LIST = Arrays.asList(DUMMY_LIST);

    @BeforeEach
    void setUp() {
        money = 10000;
        lottos = new Lottos(money, DUMMY_MANUALLOTTO_LIST);
    }

    @Test
    void checkImmutable() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            lottos.getLottos().add(new Lotto(DUMMY_LIST));
        });
    }

    @Test
    void checkSizeOfLottos() {
        Assertions.assertEquals(lottos.getLottos().size(), NUMBER_OF_LOTTOS);
    }

    @Test
    void checkSizeOfRankings() {
        List<Integer> winNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonus = 7;
        WinLotto winLotto = new WinLotto(new Lotto(winNumbers), new BonusNumber(bonus));
        Assertions.assertEquals(getSumOfMapValue(winLotto), NUMBER_OF_LOTTOS);
    }

    private Integer getSumOfMapValue(WinLotto winLotto) {
        return lottos.getRankings(winLotto)
                .getRankingResult()
                .values()
                .stream()
                .reduce(0, Integer::sum);
    }
}