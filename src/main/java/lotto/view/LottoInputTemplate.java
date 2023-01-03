package lotto.view;

import lotto.exception.ErrorCode;
import lotto.exception.LottoException;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LottoInputTemplate {
    private final Scanner scanner;

    public LottoInputTemplate() {
        scanner = new Scanner(System.in);
    }

    public Integer inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        Integer purchaseAmount = Integer.parseInt(scanner.nextLine().trim());
        validateInputPurchaseAmount(purchaseAmount);
        return purchaseAmount;
    }

    private void validateInputPurchaseAmount(Integer purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new LottoException(ErrorCode.INVALID_PURCHASE_AMOUNT);
        }
    }

    public List<Integer> inputLottoNumber() {
        System.out.println("지난 주 당첨 번호 입력해 주세요.");
        String input = scanner.nextLine().trim();

        return Arrays.stream(input.split(","))
                .map(num -> Integer.parseInt(num.trim()))
                .collect(Collectors.toList());
    }

    public Integer inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String input = scanner.nextLine().trim();
        validateBonusBall(input);
        return Integer.parseInt(input);
    }

    private void validateBonusBall(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException numberFormatException) {
            throw new LottoException(ErrorCode.INVALID_BONUS_BALL);
        }
    }
}
