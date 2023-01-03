package lottoTest.model;

import lotto.exception.ErrorCode;
import lotto.exception.LottoException;
import lotto.model.LottoNumber;
import lotto.model.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LottoTicketTest {
    private final List<LottoNumber> input = List.of(
            new LottoNumber(1), new LottoNumber(2),
            new LottoNumber(3), new LottoNumber(4),
            new LottoNumber(5), new LottoNumber(6)
    );

    @Test
    @DisplayName("정상 리스트가 인풋으로 들어왔을 때, 정상적으로 LottoTicket 생성")
    public void validateNormalLottoNumberTest() {
        //given

        //when & then
        assertDoesNotThrow(() -> new LottoTicket(input));
    }

    @ParameterizedTest
    @MethodSource("validateInvalidLottoNumberTestGenerator")
    @DisplayName("길이가 잘못된 로또 번호의 리스트가 주어지면 예외반환")
    public void validateWrongLottoTicketLengthTest(List<LottoNumber> input, ErrorCode expected) {
        //given

        //when & then
        assertThatThrownBy(() -> new LottoTicket(input))
                .isInstanceOf(LottoException.class)
                .hasMessage(expected.getMessage());

    }

    private static Stream<Arguments> validateInvalidLottoNumberTestGenerator() {
        return Stream.of(
                Arguments.of(List.of(
                        new LottoNumber(1), new LottoNumber(2),
                        new LottoNumber(3), new LottoNumber(4),
                        new LottoNumber(5), new LottoNumber(6),
                        new LottoNumber(7)
                ), ErrorCode.INVALID_LOTTO_NUMBER_LENGTH),
                Arguments.of(List.of(
                        new LottoNumber(1), new LottoNumber(2),
                        new LottoNumber(3), new LottoNumber(4),
                        new LottoNumber(5)
                ), ErrorCode.INVALID_LOTTO_NUMBER_LENGTH),
                Arguments.of(null, ErrorCode.INVALID_LOTTO_NUMBER_LENGTH)
        );
    }

    @Test
    public void includeNumberTest() {
        // given
        LottoTicket lottoTicket = new LottoTicket(input);

        // when & then
        assertThat(lottoTicket.contains(new LottoNumber(1))).isEqualTo(true);
        assertThat(lottoTicket.contains(new LottoNumber(7))).isEqualTo(false);
    }

    @ParameterizedTest
    @MethodSource("countOverlappingNumberTestGenerator")
    @DisplayName("로또 티켓끼리 겹치는 번호의 개수 반환")
    public void countOverlappingNumberTest(LottoTicket other, Integer count) {
        // given
        LottoTicket lottoTicket = new LottoTicket(input);

        // when & then
        assertThat(lottoTicket.countOverlappingNumber(other)).isEqualTo(count);
    }

    private static Stream<Arguments> countOverlappingNumberTestGenerator() {
        return Stream.of(
                Arguments.of(new LottoTicket(createLottoNumberList(List.of(1, 2, 3, 4, 5, 6))), 6),
                Arguments.of(new LottoTicket(createLottoNumberList(List.of(1, 2, 3, 4, 5, 7))), 5),
                Arguments.of(new LottoTicket(createLottoNumberList(List.of(1, 2, 3, 4, 7, 8))), 4),
                Arguments.of(new LottoTicket(createLottoNumberList(List.of(1, 2, 3, 7, 8, 9))), 3),
                Arguments.of(new LottoTicket(createLottoNumberList(List.of(1, 2, 7, 8, 9, 10))), 2),
                Arguments.of(new LottoTicket(createLottoNumberList(List.of(1, 7, 8, 9, 10, 11))), 1),
                Arguments.of(new LottoTicket(createLottoNumberList(List.of(7, 8, 9, 10, 11, 12))), 0)
        );
    }

    private static List<LottoNumber> createLottoNumberList(List<Integer> integerList) {
        return integerList.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

}
