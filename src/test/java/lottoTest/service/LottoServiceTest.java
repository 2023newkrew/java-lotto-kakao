package lottoTest.service;

import lotto.model.LottoNumber;
import lotto.model.LottoResult;
import lotto.model.LottoTicket;
import lotto.repository.LottoRepository;
import lotto.service.LottoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoServiceTest {

    @BeforeAll
    static void setUp(){
        LottoRepository.saveLottoTicket(new LottoTicket(createLottoNumberList(List.of(1,2,3,4,5,6))));
        LottoRepository.saveLottoTicket(new LottoTicket(createLottoNumberList(List.of(1,2,3,4,5,7))));
        LottoRepository.saveLottoTicket(new LottoTicket(createLottoNumberList(List.of(1,2,3,4,7,8))));
    }

    @Test
    @DisplayName("수익률 구하기 테스트")
    public void getRateOfReturnTest() {
        //given
        LottoResult lottoResult = new LottoResult(List.of(0, 0, 0, 0, 1, 0, 0, 0));
        //when
        LottoService lottoService = new LottoService();
        Double rateOfReturn = lottoService.getRateOfReturn(20000, lottoResult.getRankCountMap());
        //then
        assertThat(rateOfReturn).isEqualTo(0.25);
    }

    @ParameterizedTest
    @MethodSource("getLottoResultTestGenerator")
    @DisplayName("당첨번호와 보너스볼이 주어졌을 때, 올바른 결과를 반환하는지 테스트")
    public void getLottoResultTest(List<Integer> winningLottoNumbers, Integer bonusBall, LottoResult expected) {
        //given
        LottoService lottoService = new LottoService();

        //when & then
        assertThat(lottoService.getLottoResult(winningLottoNumbers, bonusBall))
                .isEqualTo(expected.getRankCountMap());
    }

    private static Stream<Arguments> getLottoResultTestGenerator(){
        return Stream.of(
                Arguments.of(
                        List.of(1,2,3,4,5,6),
                        7,
                        new LottoResult(List.of(1,1,0,1,0,0,0,0))
                ),
                Arguments.of(
                        List.of(1,2,3,4,5,8),
                        6,
                        new LottoResult(List.of(0,1,2,0,0,0,0,0))
                ),
                Arguments.of(
                        List.of(1,2,8,9,10,11),
                        7,
                        new LottoResult(List.of(0,0,0,0,1,2,0,0))
                ),
                Arguments.of(
                        List.of(6,7,8,9,10,11),
                        12,
                        new LottoResult(List.of(0,0,0,0,0,1,2,0))
                )
        );
    }

    private static List<LottoNumber> createLottoNumberList(List<Integer> integerList){
        return integerList.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

}
