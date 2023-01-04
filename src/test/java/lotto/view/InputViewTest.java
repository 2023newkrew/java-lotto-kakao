package lotto.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.input.InputAnalysisProcess;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputViewTest {
    InputView inputView;
    static final List<Integer> DUMMY_LIST = Arrays.asList(1,2,3,4,5,6);

    @BeforeEach
    void setUp() {
        inputView = new InputView();
    }

    @Test
    void checkValidMoneyUserInput() {
        String input = "10000";
        systemIn(input);
        Assertions.assertEquals(inputView.receiveMoneyUserInput(), 10000);
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345", "abcde"})
    void checkInvalidMoneyUserInput(String input) {
        systemIn(input);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            inputView.receiveMoneyUserInput();
        });
    }

    @Test
    void checkValidLottoNumbersUserInput(){
        String input = "1,2,3,4,5,6";
        systemIn(input);
        Assertions.assertEquals(inputView.receiveWinLottoNumbers(), DUMMY_LIST);
    }

    /**
     * 중복되는 숫자, 사이즈가 6이 아닌 입력, 숫자가 아닌 입력, 1~45 범위가 아닌 입력 체크
     * @param input invalid inputs
     */
    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,5", "1,2,3,4,5", "1,2,3,4,5,a", "51,52,53,54,56,55"})
    void checkInvalidLottoNumberUserInput(String input){
        systemIn(input);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            inputView.receiveWinLottoNumbers();
        });
    }

    @Test
    void checkValidBonusNumber(){
        String input = "7";
        systemIn(input);
        Assertions.assertEquals(inputView.receiveWinBonusNumber(DUMMY_LIST), 7);
    }

    /**
     * 1~45 범위 밖, 숫자가 아닌 입력, 로또번호에 포함되어 있는 숫자 입력 확인
     * @param input invalid inputs
     */
    @ParameterizedTest
    @ValueSource(strings = {"51", "abc", "1"})
    void checkInvalidBonusNumberUserInput(String input){
        systemIn(input);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            inputView.receiveWinBonusNumber(DUMMY_LIST);
        });
    }

    private void systemIn(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
}
