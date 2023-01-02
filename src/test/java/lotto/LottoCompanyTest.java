package lotto;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoCompanyTest {

    @DisplayName("꼴찌를 판정한다")
    @Test
    void judgeNothing() {
        LottoCompany lottoCompany = new LottoCompany(List.of(1, 2, 3, 4, 5, 6), 7);

        Prize prize = lottoCompany.judge(new Lotto(List.of(8, 9, 10, 11, 12, 13)));

        Assertions.assertThat(prize).isEqualTo(Prize.NOTHING);
    }

    @DisplayName("5등으로 판정한다")
    @Test
    void judgeFifthPrize() {
        LottoCompany lottoCompany = new LottoCompany(List.of(1, 2, 3, 4, 5, 6), 7);

        Prize prize = lottoCompany.judge(new Lotto(List.of(4, 5, 6, 7, 8, 9)));

        Assertions.assertThat(prize).isEqualTo(Prize.FIFTH);
    }

    @DisplayName("4등으로 판정한다")
    @Test
    void judgeFourthPrize() {
        LottoCompany lottoCompany = new LottoCompany(List.of(1, 2, 3, 4, 5, 6), 7);

        Prize prize = lottoCompany.judge(new Lotto(List.of(3, 4, 5, 6, 7, 8)));

        Assertions.assertThat(prize).isEqualTo(Prize.FOURTH);
    }

    @DisplayName("3등으로 판정한다")
    @Test
    void judgeThirdPrize() {
        LottoCompany lottoCompany = new LottoCompany(List.of(1, 2, 3, 4, 5, 6), 7);

        Prize prize = lottoCompany.judge(new Lotto(List.of(3, 4, 5, 6, 8, 2)));

        Assertions.assertThat(prize).isEqualTo(Prize.THIRD);
    }

    @DisplayName("2등으로 판정한다.")
    @Test
    void judgeSecondPrize() {
        LottoCompany lottoCompany = new LottoCompany(List.of(1, 2, 3, 4, 5, 6), 7);

        Prize prize = lottoCompany.judge(new Lotto(List.of(3, 4, 5, 6, 7, 2)));

        Assertions.assertThat(prize).isEqualTo(Prize.SECOND);

    }

    @DisplayName("1등으로 판정한다")
    @Test
    void judgeFirstPrize() {
        LottoCompany lottoCompany = new LottoCompany(List.of(1, 2, 3, 4, 5, 6), 7);

        Prize prize = lottoCompany.judge(new Lotto(List.of(1, 2, 3, 4, 5, 6)));

        Assertions.assertThat(prize).isEqualTo(Prize.FIRST);
    }
}
