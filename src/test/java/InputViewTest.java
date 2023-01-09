import domain.lotto.ticket.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.*;

public class InputViewTest {

    void systemIn(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @Test
    @DisplayName("수동 개수 입력 예외 테스트 (로또 총 개수 14 but 수동 개수 15개 입력)")
    void inputManualLottoNumbersExceptionTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    systemIn("15");
                    InputView.inputManualLottoNumbers(14);
                });
    }
}
