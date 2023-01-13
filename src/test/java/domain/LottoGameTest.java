package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGameTest {
    @DisplayName("당첨 번호와 로또 티켓의 번호가 순서와 상관없이 모두 같으면 일치하는 번호의 수는 6이다.")
    @Test
    void test1(){
        LottoNumber bonusNumber = new LottoNumber(45);
        WinningLotto winningLotto = new WinningLotto(new StubLottoNumbersGenerator(1, 2, 3, 4, 5, 6).generate(), bonusNumber);

        LottoNumbers lottoNumbers = new StubLottoNumbersGenerator(6, 5, 4, 3, 2, 1).generate();

        LottoGame lottoGame = new LottoGame(List.of(lottoNumbers), winningLotto);
        assertThat(lottoGame.play().getLottoMatchResults())
                .contains(new LottoMatchResult(6, false));
    }

    @DisplayName("5개의 로또 번호가 동일하고, 나머지 로또 번호가 보너스 번호와 같다면 일치하는 번호의 수는 5개이다.")
    @Test
    void test2(){
        LottoNumber bonusNumber = new LottoNumber(45);
        WinningLotto winningLotto = new WinningLotto(new StubLottoNumbersGenerator(1, 2, 3, 4, 5, 6).generate(), bonusNumber);

        LottoNumbers lottoNumbers = new StubLottoNumbersGenerator(1, 2, 3, 4, 5, 45).generate();

        LottoGame lottoGame = new LottoGame(List.of(lottoNumbers), winningLotto);
        assertThat(lottoGame.play().getLottoMatchResults())
                .contains(new LottoMatchResult(5, true));
    }

    @DisplayName("5개의 로또 번호가 동일하고, 나머지 로또 번호가 보너스 번호와 다르다면, 일치하는 번호의 수는 5개이다.")
    @Test
    void test3(){
        LottoNumber bonusNumber = new LottoNumber(45);
        WinningLotto winningLotto = new WinningLotto(new StubLottoNumbersGenerator(1, 2, 3, 4, 5, 6).generate(), bonusNumber);

        LottoNumbers lottoNumbers = new StubLottoNumbersGenerator(1, 2, 3, 4, 5, 42).generate();

        LottoGame lottoGame = new LottoGame(List.of(lottoNumbers), winningLotto);
        assertThat(lottoGame.play().getLottoMatchResults())
                .contains(new LottoMatchResult(5, false));
    }
}
