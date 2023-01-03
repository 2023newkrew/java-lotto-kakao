package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {
    @DisplayName("로또를 생성한다")
    @Test
    void 로또를_생성한다() {
        assertThatCode(()-> new Lotto()).doesNotThrowAnyException();
    }


    @DisplayName("로또를 지정된 숫자로 생성한다")
    @Test
    void 로또를_지정된_숫자로_생성한다() {
        LottoNumbers lottoNumbers = new LottoNumbers(List.of(1,2,3,4,5,6));
        assertThatCode(()-> new Lotto(lottoNumbers)).doesNotThrowAnyException();
    }

    @DisplayName("로또가 정답과 몇 개 일치하는지 판정한다")
    @Test
    void 로또가_정답과_몇_개_일치하는지_판정한다() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        LottoNumbers lottoAnswerNumbers = new LottoNumbers(List.of(1,2,3,4,5,6));
        Lotto lotto = new Lotto(lottoAnswerNumbers);
        LottoAnswer lottoAnswer = new LottoAnswer(lottoAnswerNumbers, 7);
        Method getMatchCount = Lotto.class.getDeclaredMethod("getMatchCount", LottoNumbers.class);
        getMatchCount.setAccessible(true);
        int matchCount = (int) getMatchCount.invoke(lotto, lottoAnswer.getLottoNumbers());
        assertThat(matchCount).isEqualTo(6);
    }

    @DisplayName("보너스 넘버가 포함되는지 판단한다.")
    @Test
    void 보너스_넘버가_포함되는지_판단한다 () throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        LottoNumbers lottoAnswerNumbers = new LottoNumbers(List.of(1,2,3,4,5,6));
        Lotto lotto = new Lotto(new LottoNumbers(List.of(1, 2, 3, 4, 5, 7)));
        LottoAnswer lottoAnswer = new LottoAnswer(lottoAnswerNumbers, 7);
        Method hasBonus = Lotto.class.getDeclaredMethod("hasBonus", int.class);
        hasBonus.setAccessible(true);
        boolean hasBonusResult = (boolean) hasBonus.invoke(lotto, lottoAnswer.getBonus());
        assertThat(hasBonusResult).isTrue();
    }
}
