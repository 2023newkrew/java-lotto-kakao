package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

public class UserLottosTest {
    LottoNumbers userLotto1 = new LottoNumbers(
            Arrays.asList(
                    new SingleLottoNumber(1),
                    new SingleLottoNumber(2),
                    new SingleLottoNumber(3),
                    new SingleLottoNumber(4),
                    new SingleLottoNumber(5),
                    new SingleLottoNumber(6)
            )
    );

    LottoNumbers userLotto2 = new LottoNumbers(
            Arrays.asList(
                    new SingleLottoNumber(4),
                    new SingleLottoNumber(5),
                    new SingleLottoNumber(6),
                    new SingleLottoNumber(7),
                    new SingleLottoNumber(8),
                    new SingleLottoNumber(9)
            )
    );

    LottoNumbers userLotto3 = new LottoNumbers(
            Arrays.asList(
                    new SingleLottoNumber(7),
                    new SingleLottoNumber(8),
                    new SingleLottoNumber(9),
                    new SingleLottoNumber(10),
                    new SingleLottoNumber(11),
                    new SingleLottoNumber(12)
            )
    );

    List<LottoNumbers> userLottoList = Arrays.asList(userLotto1, userLotto2, userLotto3);

    Money money = new Money(3000);

    @Test
    void 사용자_로또의_리스트와_구입한_돈을_받아서_생성한다() {
        assertThatCode(() -> new UserLottos(userLottoList, money))
                .doesNotThrowAnyException();
    }

    @Test
    void 사용자_로또의_결과를_하나씩_받아서_저장한다() {
        UserLottos userLottos = new UserLottos(userLottoList, money);

    }


}
