package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketTest {
    @DisplayName("로또는 6개의 로또 번호를 가진다")
    @Test
    void test1(){
        Assertions.assertThatThrownBy(() -> generateLotto(1, 2, 3, 4, 5))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또는 중복된 로또 번호를 가질 수 없다")
    @Test
    void test2(){
        Assertions.assertThatThrownBy(() -> generateLotto(1, 2, 3, 4, 5, 5))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private LottoTicket generateLotto(int... values) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();

        for(int value : values){
            lottoNumbers.add(new LottoNumber(value));
        }
        return new LottoTicket(lottoNumbers);
    }
}
