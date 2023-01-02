import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class LottoCheckerTest {

    @Test
    @DisplayName("로또 꽝 당첨 테스트")
    void checkLottoFail(){
        // given
        LottoChecker lottoChecker = new LottoChecker();
        LottoTicket lottoTicket = new LottoTicket(new ArrayList<>(List.of(6,7,8,9,10,11)));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1,2,3,4,5,45), 7);

        // when
        LottoResult lottoResult = lottoChecker.getResult(lottoTicket, winningNumbers);

        // then
        assertThat(lottoResult).isEqualTo(LottoResult.FAIL);
    }

    @Test
    @DisplayName("로또 5등 당첨 테스트")
    void checkLotto5thPlace(){
        // given
        LottoChecker lottoChecker = new LottoChecker();
        LottoTicket lottoTicket = new LottoTicket(new ArrayList<>(List.of(1,2,3,4,5,7)));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1,2,3,43,44,45), 7);

        // when
        LottoResult lottoResult = lottoChecker.getResult(lottoTicket, winningNumbers);

        // then
        assertThat(lottoResult).isEqualTo(LottoResult.FIFTH_PLACE);
    }
    @Test
    @DisplayName("로또 4등 당첨 테스트")
    void checkLotto4thPlace(){
        // given
        LottoChecker lottoChecker = new LottoChecker();
        LottoTicket lottoTicket = new LottoTicket(new ArrayList<>(List.of(1,2,3,4,9,7)));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1,2,3,4,5,45), 7);

        // when
        LottoResult lottoResult = lottoChecker.getResult(lottoTicket, winningNumbers);

        // then
        assertThat(lottoResult).isEqualTo(LottoResult.FOURTH_PLACE);
    }
    @Test
    @DisplayName("로또 3등 당첨 테스트")
    void checkLotto3rdPlace(){
        // given
        LottoChecker lottoChecker = new LottoChecker();
        LottoTicket lottoTicket = new LottoTicket(new ArrayList<>(List.of(1,2,3,4,5,7)));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1,2,3,4,5,45), 42);

        // when
        LottoResult lottoResult = lottoChecker.getResult(lottoTicket, winningNumbers);

        // then
        assertThat(lottoResult).isEqualTo(LottoResult.THIRD_PLACE);
    }
    @Test
    @DisplayName("로또 2등 당첨 테스트")
    void checkLotto2ndPlace(){
        // given
        LottoChecker lottoChecker = new LottoChecker();
        LottoTicket lottoTicket = new LottoTicket(new ArrayList<>(List.of(1,2,3,4,5,7)));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1,2,3,4,5,45), 7);

        // when
        LottoResult lottoResult = lottoChecker.getResult(lottoTicket, winningNumbers);

        // then
        assertThat(lottoResult).isEqualTo(LottoResult.SECOND_PLACE);
    }

    @Test
    @DisplayName("로또 1등 당첨 테스트")
    void checkLotto1stPlace(){
        // given
        LottoChecker lottoChecker = new LottoChecker();
        LottoTicket lottoTicket = new LottoTicket(new ArrayList<>(List.of(1,2,3,4,5,7)));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1,2,3,4,5,7), 12);

        // when
        LottoResult lottoResult = lottoChecker.getResult(lottoTicket, winningNumbers);

        // then
        assertThat(lottoResult).isEqualTo(LottoResult.FIRST_PLACE);
    }
}
