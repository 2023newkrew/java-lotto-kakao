package lotto;

import lotto.domain.LottoTicket;
import lotto.domain.LottoWinnerTicket;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class LottoCalculatorTest {
    @Test
    @DisplayName("당첨 번호와 일치하는 개수를 계산해야 한다.")
    void lottoCorrectNumberTest(){
        // 당첨 번호
        LottoWinnerTicket winTicket = new LottoWinnerTicket(
                new LottoTicket(List.of(1, 2, 3, 4, 5, 6)), 7);

        // 사용자가 뽑은 로또 번호들
        LottoTicket userTicket1 = new LottoTicket(List.of(1, 2, 3, 9, 10, 11));
        LottoTicket userTicket2 = new LottoTicket(List.of(1, 2, 3, 4, 9, 11));
        LottoTicket userTicket3 = new LottoTicket(List.of(1, 2, 3, 4, 5, 11));


        assertThat(winTicket.checkSameCount(userTicket1)).isEqualTo(3);
        assertThat(winTicket.checkSameCount(userTicket2)).isEqualTo(4);
        assertThat(winTicket.checkSameCount(userTicket3)).isEqualTo(5);
    }

    @Test
    @DisplayName("보너스 볼과 일치하는 볼이 있는지 확인할 수 있어야 한다.")
    void lottoBonusCheckTest(){
        LottoWinnerTicket winTicket = new LottoWinnerTicket(
                new LottoTicket(List.of(1, 2, 3, 4, 5, 6)), 22);

        LottoTicket userTicket1 = new LottoTicket(List.of(1, 3, 4, 5, 6, 22));
        Assertions.assertThat(winTicket.isBonusNumber(userTicket1, 5)).isTrue();

        LottoTicket userTicket2 = new LottoTicket(List.of(1, 3, 4, 5, 6, 23));
        Assertions.assertThat(winTicket.isBonusNumber(userTicket2, 5)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3,   4, 5, 6, 7", " 1,   2, 3"})
    @DisplayName("당첨 번호가 6개가 아닌 경우 예외를 발생한다.")
    void lottoWinNumberCountTest(String userInput){
        Integer[] splitNumbers = changeToArray(userInput);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new LottoTicket(Arrays.asList(splitNumbers)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,46", "1,99,100,2,3,7"})
    @DisplayName("당첨 번호가 1 ~ 45 사이의 정수가 아니라면, 예외를 발생한다.")
    void lottoWinNumberRangeTest(String userInput){
        Integer[] splitNumbers = changeToArray(userInput);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new LottoTicket(Arrays.asList(splitNumbers)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,2,3,4,5", "1,3,4,5,10,10"})
    @DisplayName("당첨 번호가 중복된 수가 아니어야 한다.")
    void lottoDuplicatedNumberTest(String userInput){
        Integer[] splitNumbers = changeToArray(userInput);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new LottoTicket(Arrays.asList(splitNumbers)));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("보너스 볼이 1 ~ 45 사이의 정수가 아니라면, 예외를 발생한다.")
    void lottoBonusNumberTest(int bonus){
        LottoTicket lottoTicket = new LottoTicket(List.of(1, 2, 3, 4, 5, 6));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new LottoWinnerTicket(lottoTicket, bonus));
    }

    private Integer[] changeToArray(String userInput){
        return Stream.of(userInput
                        .replace(" ", "")
                        .split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toArray(Integer[]::new);
    }
}
