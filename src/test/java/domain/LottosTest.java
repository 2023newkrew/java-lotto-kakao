package domain;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    private static Lottos lottos;

    @BeforeAll
    static void setUp() {
        lottos = new Lottos();
        lottos.add("3, 5, 11, 16, 32, 38");
    }

    @Test
    void addTest() {
        int beforeSize = lottos.getSize();
        String newLottoNumbers = "8, 21, 23, 41, 42, 43";
        lottos.add(newLottoNumbers);
        int afterSize = lottos.getSize();
        assertThat(afterSize).isEqualTo(beforeSize + 1);
    }

}
