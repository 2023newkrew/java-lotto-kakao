package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserLottoTest {

    LottoNumbers winningLottoNumbers = new LottoNumbers(
            Arrays.asList(
                    LottoNumber.from(1),
                    LottoNumber.from(2),
                    LottoNumber.from(3),
                    LottoNumber.from(4),
                    LottoNumber.from(5),
                    LottoNumber.from(6)
            )
    );
    LottoNumber bonusNumber = LottoNumber.from(7);

    WinningLotto winningLotto = new WinningLotto(winningLottoNumbers, bonusNumber);

    @Test
    void 정답과_6개가_모두_일치하면_1등상을_받아야_한다() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
        );

        LottoNumbers lottoNumber = new LottoNumbers(lottoNumbers);
        UserLotto userLotto = new UserLotto(lottoNumber);

        assertThat(userLotto.getLottoPrize(winningLotto)).isEqualTo(LottoPrize.FIRST_PRIZE);
    }

    @Test
    void 정답과_5개가_일치하고_보너스_숫자와_일치하면_2등상을_받아야_한다() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(7)
        );

        LottoNumbers lottoNumber = new LottoNumbers(lottoNumbers);
        UserLotto userLotto = new UserLotto(lottoNumber);

        assertThat(userLotto.getLottoPrize(winningLotto)).isEqualTo(LottoPrize.SECOND_PRIZE);
    }

    @Test
    void 정답과_5개가_일치하면_3등상을_받아야_한다() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(45)
        );

        LottoNumbers lottoNumber = new LottoNumbers(lottoNumbers);
        UserLotto userLotto = new UserLotto(lottoNumber);

        assertThat(userLotto.getLottoPrize(winningLotto)).isEqualTo(LottoPrize.THIRD_PRIZE);
    }

    @Test
    void 정답과_4개가_일치하면_4등상을_받아야_한다() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(44),
                LottoNumber.from(45)
        );

        LottoNumbers lottoNumber = new LottoNumbers(lottoNumbers);
        UserLotto userLotto = new UserLotto(lottoNumber);

        assertThat(userLotto.getLottoPrize(winningLotto)).isEqualTo(LottoPrize.FOURTH_PRIZE);
    }

    @Test
    void 정답과_3개가_일치하면_5등상을_받아야_한다() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(43),
                LottoNumber.from(44),
                LottoNumber.from(45)
        );

        LottoNumbers lottoNumber = new LottoNumbers(lottoNumbers);
        UserLotto userLotto = new UserLotto(lottoNumber);

        assertThat(userLotto.getLottoPrize(winningLotto)).isEqualTo(LottoPrize.FIFTH_PRIZE);
    }

    @Test
    void 정답과_3개_미만이_일치하면_아무것도_받지_못한다() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                LottoNumber.from(40),
                LottoNumber.from(41),
                LottoNumber.from(42),
                LottoNumber.from(43),
                LottoNumber.from(44),
                LottoNumber.from(45)
        );

        LottoNumbers lottoNumber = new LottoNumbers(lottoNumbers);
        UserLotto userLotto = new UserLotto(lottoNumber);

        assertThat(userLotto.getLottoPrize(winningLotto)).isEqualTo(LottoPrize.NONE);
    }
}
