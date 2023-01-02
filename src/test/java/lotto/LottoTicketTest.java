package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketTest {

    @DisplayName("개수가 6개가 아닌 모든 경우.")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "''                 |",
            "'1'                |",
            "'1,2,3,4,5'        |",
            "'1,2,3,4,5,6,7'    |",
    })
    void invalidBallCount(String lottoNumbers) {
        assertThatThrownBy(() -> new LottoTicket(Arrays.stream(lottoNumbers.split(","))
                .map(v -> new LottoBall(Integer.parseInt(v)))
                .collect(Collectors.toList())));
    }


    @DisplayName("중복 번호가 존재하는 경우")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "'5,5,5,5,5,5'   |",
            "'1,5,5,5,5,5'   |",
            "'1,2,5,5,5,5'   |",
            "'1,2,3,5,5,5'   |",
            "'1,2,3,4,5,5'   |",
    })
    void hasDuplicatedBall(String lottoNumbers) {
        assertThatThrownBy(() -> new LottoTicket(Arrays.stream(lottoNumbers.split(","))
                .map(v -> new LottoBall(Integer.parseInt(v)))
                .collect(Collectors.toList())));
    }

    @DisplayName("두 객체간의 동등성 비교")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "'1,2,3,4,5,6'  | '1,2,3,4,5,6'     | true",
            "'1,2,3,4,5,6'  | '6,5,4,3,2,1'     | true",
            "'1,2,3,4,5,6'  | '2,3,4,5,6,7'     | false",
    })
    void _lotto_not_equal(String lottoBallsA, String lottoBallsB, boolean expectedEquality) {
        LottoTicket lottoA = new LottoTicket(Arrays.stream(lottoBallsA.split(","))
                .map(v -> new LottoBall(Integer.parseInt(v)))
                .collect(Collectors.toList()));
        LottoTicket lottoB = new LottoTicket(Arrays.stream(lottoBallsB.split(","))
                .map(v -> new LottoBall(Integer.parseInt(v)))
                .collect(Collectors.toList()));
        assertThat(lottoA.equals(lottoB)).isEqualTo(expectedEquality);
    }
}
