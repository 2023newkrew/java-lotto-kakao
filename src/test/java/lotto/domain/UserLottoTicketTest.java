package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

public class UserLottoTicketTest {
    LottoNumbers userLotto1 = new LottoNumbers(
            Arrays.asList(
                    LottoNumber.from(1),
                    LottoNumber.from(2),
                    LottoNumber.from(3),
                    LottoNumber.from(4),
                    LottoNumber.from(5),
                    LottoNumber.from(6)
            )
    );

    LottoNumbers userLotto2 = new LottoNumbers(
            Arrays.asList(
                    LottoNumber.from(4),
                    LottoNumber.from(5),
                    LottoNumber.from(6),
                    LottoNumber.from(7),
                    LottoNumber.from(8),
                    LottoNumber.from(9)
            )
    );

    LottoNumbers userLotto3 = new LottoNumbers(
            Arrays.asList(
                    LottoNumber.from(7),
                    LottoNumber.from(8),
                    LottoNumber.from(9),
                    LottoNumber.from(10),
                    LottoNumber.from(11),
                    LottoNumber.from(12)
            )
    );

    List<UserLotto> manualLottoList = Arrays.asList(new UserLotto(userLotto1), new UserLotto(userLotto2), new UserLotto(userLotto3));
    List<UserLotto> randomLottoList = LottoSeller.generateRandomLottos(3);

    @Test
    void 사용자_로또의_리스트를_받아서_생성한다() {
        assertThatCode(() -> new UserLottoTicket(randomLottoList, manualLottoList))
                .doesNotThrowAnyException();
    }

}
