package lotto;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.LottoWinnerTicket;
import lotto.utils.StringConversion;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;
import java.util.stream.Collectors;

import static lotto.utils.Constants.LOTTO_TICKET_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class NumberValidateTest {
    private StringConversion stringConversion;
    private LottoTicket userTicket;

    @BeforeEach
    void setUp(){
        this.stringConversion = new StringConversion();

        List<Integer> userTicketList1 = List.of(1, 2, 3, 8, 9, 11);
        this.userTicket = new LottoTicket(new ArrayList<>(userTicketList1.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList())));
    }

    @Test
    @DisplayName("6개의 숫자에 중복이 없어야 한다.")
    void lottoNumberDupTest() {
        LottoTickets lottoTickets = new LottoTickets(1000);
        lottoTickets.createRandomTickets(1);
        for (LottoTicket lottoTicket: lottoTickets.getTickets()){
            Set<Integer> dupCheck = new HashSet<>();
            for (LottoNumber lottoNumber : lottoTicket.getLottoNumbers()) {
                assertThat(dupCheck.contains(lottoNumber.getNumber())).isFalse();
                dupCheck.add(lottoNumber.getNumber());
            }
        }
    }

    @RepeatedTest(20)
    @DisplayName("랜덤 숫자가 1~45 사이에 존재해야만 한다.")
    void lottoNumberRangeTest() {
        LottoTickets lottoTickets = new LottoTickets(1000);
        lottoTickets.createRandomTickets(1);

        for (LottoTicket lottoTicket: lottoTickets.getTickets()){
            for (int i = 0; i < LOTTO_TICKET_SIZE; i++) {
                Assertions.assertThat(lottoTicket.getLottoNumbers()
                    .get(i).getNumber())
                    .isGreaterThanOrEqualTo(1)
                    .isLessThanOrEqualTo(45);
            }
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3,   4, 5, 6, 7", " 1,   2, 3"})
    @DisplayName("당첨 번호가 6개가 아닌 경우 예외를 발생한다.")
    void lottoWinNumberCountTest(String userNumber){
        LottoNumber[] splitNumbers = stringConversion.convertToLottoArray(userNumber);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new LottoTicket(new ArrayList<>(Arrays.asList(splitNumbers))));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,46", "1,99,100,2,3,7"})
    @DisplayName("당첨 번호가 1 ~ 45 사이의 정수가 아니라면, 예외를 발생한다.")
    void lottoWinNumberRangeTest(String userNumber){
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    LottoNumber[] splitNumbers = stringConversion.convertToLottoArray(userNumber);
                    new LottoTicket(new ArrayList<>(Arrays.asList(splitNumbers)));
                });
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,2,3,4,5", "1,3,4,5,10,10"})
    @DisplayName("당첨 번호가 중복된 수가 아니어야 한다.")
    void lottoDuplicatedNumberTest(String userNumber){
        LottoNumber[] splitNumbers = stringConversion.convertToLottoArray(userNumber);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new LottoTicket(new ArrayList<>(Arrays.asList(splitNumbers))));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("보너스 볼이 1 ~ 45 사이의 정수가 아니라면, 예외를 발생한다.")
    void lottoBonusNumberTest(int bonus){
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new LottoWinnerTicket(userTicket, new LottoNumber(bonus)));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("보너스 볼이 당첨번호에 포함된다면, 예외가 발생한다.")
    void bonusAlreadyExistsInNumbersTest(int bonus){
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new LottoWinnerTicket(userTicket, new LottoNumber(bonus)));
    }
}
