package lotto;

import lotto.domain.LottoRandom;
import lotto.domain.LottoTicket;
import lotto.domain.LottoWinnerTicket;
import lotto.utils.StringConversion;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

import static lotto.utils.Constants.LOTTO_TICKET_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class NumberValidateTest {
    @Test
    @DisplayName("6개의 숫자에 중복이 없어야 한다.")
    void lottoNumberDupTest() {
        LottoRandom lottoRandom = new LottoRandom();
        ArrayList<Integer> lottoNumbers = lottoRandom.createRandomNumbers(); // 자동으로 생성되는 로또 번호들
        // 숫자 중복이 없는지 확인
        Set<Integer> dupCheck = new HashSet<>();
        for (int num : lottoNumbers) {
            assertThat(dupCheck.contains(num)).isFalse();
            dupCheck.add(num);
        }
    }

    @RepeatedTest(20)
    @DisplayName("랜덤 숫자가 1~45 사이에 존재해야만 한다.")
    void lottoNumberRangeTest() {
        LottoRandom lottoRandom = new LottoRandom();
        LottoTicket lottoTicket = new LottoTicket(lottoRandom.createRandomNumbers());
        for (int i = 0; i < LOTTO_TICKET_SIZE; i++) {
            Assertions.assertThat(lottoTicket.getLottoNumbers().get(i)).
                    isGreaterThanOrEqualTo(1).
                    isLessThanOrEqualTo(45);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3,   4, 5, 6, 7", " 1,   2, 3"})
    @DisplayName("당첨 번호가 6개가 아닌 경우 예외를 발생한다.")
    void lottoWinNumberCountTest(String userNumber){
        StringConversion stringConversion = new StringConversion();
        Integer[] splitNumbers = stringConversion.convertToArray(userNumber);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new LottoTicket(new ArrayList<>(Arrays.asList(splitNumbers))));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,46", "1,99,100,2,3,7"})
    @DisplayName("당첨 번호가 1 ~ 45 사이의 정수가 아니라면, 예외를 발생한다.")
    void lottoWinNumberRangeTest(String userNumber){
        StringConversion stringConversion = new StringConversion();
        Integer[] splitNumbers = stringConversion.convertToArray(userNumber);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new LottoTicket(new ArrayList<>(Arrays.asList(splitNumbers))));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,2,3,4,5", "1,3,4,5,10,10"})
    @DisplayName("당첨 번호가 중복된 수가 아니어야 한다.")
    void lottoDuplicatedNumberTest(String userNumber){
        StringConversion stringConversion = new StringConversion();
        Integer[] splitNumbers = stringConversion.convertToArray(userNumber);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new LottoTicket(new ArrayList<>(Arrays.asList(splitNumbers))));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("보너스 볼이 1 ~ 45 사이의 정수가 아니라면, 예외를 발생한다.")
    void lottoBonusNumberTest(int bonus){
        LottoTicket lottoTicket = new LottoTicket(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new LottoWinnerTicket(lottoTicket, bonus));
    }
}
