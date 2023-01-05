package lotto.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
    void scanWinningNumberString() {
        InputView inputView = new InputView(createScanner("1,2,3,4,5,6"));
        String winningNumberString = inputView.scanWinningNumberString();
        assertThat(winningNumberString).isEqualTo("1,2,3,4,5,6");
    }

    @Test
    @DisplayName("로또 보너스 번호 입력")
    void scanBonusNumber() {
        InputView inputView = new InputView(createScanner("7"));
        int bonusNumber = inputView.scanBonusNumber();
        assertThat(bonusNumber).isEqualTo(7);
    }

    private static Scanner createScanner(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner sc = new Scanner(in);
        return sc;
    }
}