package lottoTest.service;

import lotto.model.LottoResult;
import lotto.service.LottoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoServiceTest {

    @Test
    @DisplayName("수익률 구하기 테스트")
    public void getRateOfReturnTest() {
        //given
        LottoResult lottoResult = new LottoResult(List.of(0, 0, 0, 0, 1, 0));
        //when
        LottoService lottoService = new LottoService();
        Double rateOfReturn = lottoService.getRateOfReturn(20000, lottoResult.getRankCountMap());
        //then
        assertThat(rateOfReturn).isEqualTo(0.25);
    }
}
