package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

public class UserLottosTest {
    LottoNumbers userLotto1 = new LottoNumbers(
            Arrays.asList(
                    new LottoNumber(1),
                    new LottoNumber(2),
                    new LottoNumber(3),
                    new LottoNumber(4),
                    new LottoNumber(5),
                    new LottoNumber(6)
            )
    );

    LottoNumbers userLotto2 = new LottoNumbers(
            Arrays.asList(
                    new LottoNumber(4),
                    new LottoNumber(5),
                    new LottoNumber(6),
                    new LottoNumber(7),
                    new LottoNumber(8),
                    new LottoNumber(9)
            )
    );

    LottoNumbers userLotto3 = new LottoNumbers(
            Arrays.asList(
                    new LottoNumber(7),
                    new LottoNumber(8),
                    new LottoNumber(9),
                    new LottoNumber(10),
                    new LottoNumber(11),
                    new LottoNumber(12)
            )
    );

    List<LottoNumbers> userLottoList = Arrays.asList(userLotto1, userLotto2, userLotto3);


    @Test
    void 사용자_로또의_리스트를_받아서_생성한다() {
        assertThatCode(() -> new UserLottos(userLottoList))
                .doesNotThrowAnyException();
    }

}
