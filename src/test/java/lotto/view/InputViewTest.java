package lotto.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.input.InputAnalysisProcess;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;

public class InputViewTest {
    InputView inputView = new InputView();
    static final List<Integer> DUMMY_LIST = Arrays.asList(1,2,3,4,5,6);

    @Test
    void checkThousandUnit() {
        String input = "10000";
        systemIn(input);
        Assertions.assertEquals(inputView.receiveMoneyUserInput(), 10000);
    }
    @Test
    void checkNotThousandUnit() {
        String input = "12345\n";
        systemIn(input);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            inputView.receiveMoneyUserInput();
        });
    }

    @Test
    void checkStringInput() {
        String input = "abcde\n";
        systemIn(input);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            inputView.receiveMoneyUserInput();
        });
    }

    @Test
    void checkLastLottoNumbers(){
        String input = "1,2,3,4,5,6\n";
        systemIn(input);
        Assertions.assertEquals(inputView.receiveLastLottoNumbers(), Arrays.asList(1,2,3,4,5,6));
    }

    @Test
    void checkLastLottoNumbersDuplicate(){
        String input = "1,2,3,4,5,5\n";
        systemIn(input);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            inputView.receiveLastLottoNumbers();
        });
    }

    @Test
    void checkLastLottoNumbersWrongSize(){
        String input = "1,2,3,4,5\n";
        systemIn(input);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            inputView.receiveLastLottoNumbers();
        });
    }

    @Test
    void checkLastLottoNumbersString(){
        String input = "1,2,3,4,5,a\n";
        systemIn(input);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            inputView.receiveLastLottoNumbers();
        });
    }

    @Test
    void checkLastLottoNumbersOutRange(){
        String input = "51,52,53,54,56,55\n";
        systemIn(input);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            inputView.receiveLastLottoNumbers();
        });
    }

    @Test
    void checkLastLottoBonusNumber(){
        String input = "7\n";
        systemIn(input);
        Assertions.assertEquals(inputView.receiveLastLottoBonusNumber(Arrays.asList(1,2,3,4,5,6)), 7);
    }

    @Test
    void checkLastLottoBonusNumberOutRange(){
        String input = "51\n";
        systemIn(input);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            inputView.receiveLastLottoBonusNumber(DUMMY_LIST);
        });
    }

    @Test
    void checkLastLottoBonusNumberString(){
        String input = "abc\n";
        systemIn(input);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            inputView.receiveLastLottoBonusNumber(DUMMY_LIST);
        });
    }

    @Test
    void checkLastLottoBonusNumberDuplicateWithLottoNumbers(){
        String input = "1\n";
        systemIn(input);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            inputView.receiveLastLottoBonusNumber(DUMMY_LIST);
        });
    }

    protected void systemIn(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
}
