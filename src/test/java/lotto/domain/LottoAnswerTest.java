package lotto.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoAnswerTest {


    @Test
    @DisplayName("LottoAnswer_생성_테스트")
    void LottoAnswer_생성_테스트(){
        Assertions.assertThatCode(() -> new LottoAnswer(new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)), 7))
                .doesNotThrowAnyException();
    }

}
