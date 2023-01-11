package lotto.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {

    @DisplayName("당첨 결과를 집계한다.")
    @Test
    void judgeLotto() {
        // arrange
        LottoNumber bonus = new LottoNumber(7);
        Lotto firstPrizeLotto = Lotto.create(1, 2, 3, 4, 5, 6);
        Lotto thridPrizeLotto = Lotto.create(1, 2, 3, 4, 5, 8);
        Lotto fifthPrizeLotto = Lotto.create(1, 2, 3, 7, 8, 9);
        WinningNumbers winningNumbers = new WinningNumbers(firstPrizeLotto, bonus);

        Money money = new Money(6000);
        LottosGenerator generator = count -> List.of(
                firstPrizeLotto,
                thridPrizeLotto, thridPrizeLotto,
                fifthPrizeLotto, fifthPrizeLotto, fifthPrizeLotto
        );
        LottoMachine sut = LottoMachine.create(generator, money);

        // act
        WinningStatistics actual = sut.judge(winningNumbers);

        // assert
        assertThat(actual.countBy(Prize.FIRST)).isEqualTo(1L);
        assertThat(actual.countBy(Prize.SECOND)).isEqualTo(0L);
        assertThat(actual.countBy(Prize.THIRD)).isEqualTo(2L);
        assertThat(actual.countBy(Prize.FOURTH)).isEqualTo(0L);
        assertThat(actual.countBy(Prize.FIFTH)).isEqualTo(3L);
        assertThat(actual.countBy(Prize.NOTHING)).isEqualTo(0L);
    }

    @DisplayName("발급한 로또를 확인한다.")
    @Test
    void getIssuedLottos() {
        LottoMachine sut = LottoMachine.create(new RandomLottosGenerator(), new Money(13400));

        Assertions.assertThat(sut.getIssueLottos())
                .hasSize(13);
    }
}