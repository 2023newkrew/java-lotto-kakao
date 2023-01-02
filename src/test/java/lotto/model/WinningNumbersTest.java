package lotto.model;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class WinningNumbersTest {
    @Test
    @DisplayName("6개의 숫자가 일치하는 경우 1등에 해당한다.")
    void matchTest() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Price price = winningNumbers.getPrice(userLotto); //-> match, bounusBall

        assertThat(price).isEqualTo(Price.FIRST);
    }

    @Test
    @DisplayName("5개의 숫자가 일치하고 보너스 볼을 맞춘 경우 2등에 해당한다.")
    void matchTest2() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Price price = winningNumbers.getPrice(userLotto); //-> match, bounusBall

        assertThat(price).isEqualTo(Price.SECOND);
    }

    @Test
    @DisplayName("5개의 숫자가 일치하고 보너스 볼을 맞추지 못한 경우 3등에 해당한다.")
    void matchTest3() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 100));
        Price price = winningNumbers.getPrice(userLotto); //-> match, bounusBall

        assertThat(price).isEqualTo(Price.THIRD);
    }

    @Test
    @DisplayName("4개의 숫자가 일치한 경우 4등에 해당한다.")
    void matchTest4() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 100, 101));
        Price price = winningNumbers.getPrice(userLotto); //-> match, bounusBall

        assertThat(price).isEqualTo(Price.FOURTH);
    }

    @Test
    @DisplayName("3개의 숫자가 일치한 경우 5등에 해당한다.")
    void matchTest5() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 100, 101, 102));
        Price price = winningNumbers.getPrice(userLotto); //-> match, bounusBall

        assertThat(price).isEqualTo(Price.FIFTH);
    }
}
