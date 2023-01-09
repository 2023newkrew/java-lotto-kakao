import domain.lotto.number.LottoNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class RandomNumberTest {

    @Test
    @DisplayName("랜덤 숫자를 생성하는 기능")
    void generateRandomNumberTest() {
        // when
        LottoNumbers lottoNumbers = LottoNumbers.create(() -> new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)));

        // then
        assertThat(lottoNumbers.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }
}
