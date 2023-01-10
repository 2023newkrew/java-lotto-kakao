package lotto.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

class InputViewTest {
    @Test
    @DisplayName("로또 구입 금액 입력")
    void scanPurchaseAmount() {
        InputView inputView = new InputView(createScanner("14000"));
        int purchaseAmount = inputView.scanPurchaseAmount();
        assertThat(purchaseAmount).isEqualTo(14000);
    }

    @Test
    @DisplayName("로또 당첨 번호 입력")
    void scanWinningNumbers() {
        InputView inputView = new InputView(createScanner("1,2,3,4,5,6"));
        List<Integer> winningNumbers = inputView.scanWinningNumbers();
        assertThat(winningNumbers).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("로또 보너스 번호 입력")
    void scanBonusNumber() {
        InputView inputView = new InputView(createScanner("7"));
        int bonusNumber = inputView.scanBonusNumber();
        assertThat(bonusNumber).isEqualTo(7);
    }

    @Test
    @DisplayName("수동 로또 개수 입력")
    void scanNumberOfManualLotto() {
        InputView inputView = new InputView(createScanner("3"));
        int numberOfManualLotto = inputView.scanNumberOfManualLotto();
        assertThat(numberOfManualLotto).isEqualTo(3);
    }

    @Test
    @DisplayName("수동 로또 번호 입력")
    void scanManualLottoNumbers() {
        InputView inputView = new InputView(createScanner("1,2,3,4,5,6\n7,8,9,10,11,12"));
        List<List<Integer>> manualLottoNumbers = inputView.scanManualLottoNumbers(2);
        assertThat(manualLottoNumbers.get(0)).containsExactly(1, 2, 3, 4, 5, 6);
        assertThat(manualLottoNumbers.get(1)).containsExactly(7, 8, 9, 10, 11, 12);
    }

    private static Scanner createScanner(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner sc = new Scanner(in);
        return sc;
    }
}