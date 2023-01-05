package lottoTest.service;

import lotto.exception.ErrorCode;
import lotto.exception.LottoException;
import lotto.model.LottoTicket;
import lotto.service.LottoMachine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class LottoMachineTest {
    private final LottoMachine lottoMachine;

    public LottoMachineTest() {
        this.lottoMachine = new LottoMachine();
    }

    @Test
    @DisplayName("랜덤한 로또 티켓 생성 테스트")
    void createRandomLottoTicketTest(){
        //given

        //when & then
        assertThat(lottoMachine.createRandomLottoTicket())
                .isInstanceOf(LottoTicket.class);
    }

    @Test
    @DisplayName("정상 범위의 정수 리스트가 주어졌을 때 로또 티켓 생성 테스트")
    void createNormalManualLottoTicketTest(){
        //given
        List<Integer> input = List.of(1,2,3,4,5,6);

        //when & then
        assertThat(lottoMachine.createManualLottoTicket(input))
                .isInstanceOf(LottoTicket.class);
    }

    @ParameterizedTest
    @MethodSource("createInvalidManualLottoTicketTestGenerator")
    @DisplayName("정상 범위 혹 길이가 아닌 정수 리스트가 주여졌을 때 로또 예외 반환 테스트")
    void createInvalidManualLottoTicketTest(List<Integer> input, ErrorCode expected){
        //given

        //when & then
        assertThatThrownBy(() -> lottoMachine.createManualLottoTicket(input))
                .isInstanceOf(LottoException.class)
                .hasMessage(expected.getMessage());
    }

    private static Stream<Arguments> createInvalidManualLottoTicketTestGenerator(){
        return Stream.of(
                Arguments.of(List.of(0,1,2,3,4,5), ErrorCode.INVALID_LOTTO_NUMBER_RANGE),
                Arguments.of(List.of(1,2,3,4,5,46), ErrorCode.INVALID_LOTTO_NUMBER_RANGE),
                Arguments.of(List.of(1,2,3,4,5), ErrorCode.INVALID_LOTTO_NUMBER_LENGTH),
                Arguments.of(List.of(1,2,3,4,5,6,7), ErrorCode.INVALID_LOTTO_NUMBER_LENGTH)
        );
    }
}
