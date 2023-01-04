package lotto.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class LottosTest {
    int input;
    Lottos lottos;
    private final LottosFactory lottosFactory = new LottosFactory();

    @BeforeEach
    void setUp() {
        input = 10;
        lottos = lottosFactory.makeLottosAuto(input);
    }
    @Test
    void checkSizeOfLottos() {
        Assertions.assertEquals(lottos.getLottos().size(), 10);
    }

    @Test
    void checkSizeOfRankings(){
        List<Integer> winNumbers = Arrays.asList(1,2,3,4,5,6);
        int bonus = 7;
        Assertions.assertEquals(lottos.getRankings(new WinningNumbers(winNumbers, bonus)).size(), 10);
    }
}