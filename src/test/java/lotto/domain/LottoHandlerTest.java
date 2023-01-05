package lotto.domain;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoHandlerTest {

    @Test
    @DisplayName("출력_포맷에_맞는_결과를_출력한다")
    void 출력_포맷에_맞는_결과를_출력한다() {
        List<LottoNumbers> lottoNumbers = List.of(LottoNumbers.createLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        LottoHandler lottoHandler = LottoHandler.createLottoHandler();
        lottoHandler.createLottos(lottoNumbers, 0);
        lottoHandler.setLottoAnswer(List.of(1, 2, 3, 7, 8, 9), 5);
        System.out.println((lottoHandler.getLottoResultString()));

    }

}