package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

public class UserLottoTest {
    @Test
    void LottoNumbers를_가지고_생성된다() {
        List<SingleLottoNumber> singleLottoNumbers = Arrays.asList(
                new SingleLottoNumber(1),
                new SingleLottoNumber(2),
                new SingleLottoNumber(3),
                new SingleLottoNumber(4),
                new SingleLottoNumber(5),
                new SingleLottoNumber(6)
        );

        LottoNumbers lottoNumbers = new LottoNumbers(singleLottoNumbers);

        assertThatCode(() -> new UserLotto(lottoNumbers)).doesNotThrowAnyException();
    }

}
