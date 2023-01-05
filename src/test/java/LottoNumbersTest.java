import domain.lotto.number.LottoNumbers;
import exception.InvalidLottoNumbersException;
import exception.LottoNumberOutOfRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LottoNumbersTest {

    @Test
    @DisplayName("로또 번호는 1 이상 45 이하의 6자리 숫자")
    public void validateLottoNumbersRange() {
        assertThatExceptionOfType(LottoNumberOutOfRangeException.class)
                .isThrownBy(() -> new LottoNumbers(List.of(0, 2, 3, 4, 5, 6)));
        assertThatExceptionOfType(LottoNumberOutOfRangeException.class)
                .isThrownBy(() -> new LottoNumbers(List.of(1, 2, 3, 4, 5, 46)));
    }

    @Test
    @DisplayName("로또 번호는 중복되지 않는 6자리 숫자임")
    public void validateLottoNumbersDuplication() {
        assertThatExceptionOfType(InvalidLottoNumbersException.class)
                .isThrownBy(() -> new LottoNumbers(List.of(1, 1, 3, 4, 5, 6)));
        assertThatExceptionOfType(InvalidLottoNumbersException.class)
                .isThrownBy(() -> new LottoNumbers(List.of(1,2,3,4,5)));
        assertThatExceptionOfType(InvalidLottoNumbersException.class)
                .isThrownBy(() -> new LottoNumbers(List.of(1,2,3,4,5,6,7)));
    }


}
