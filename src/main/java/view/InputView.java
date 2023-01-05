package view;

import domain.dto.WinningNumbersDto;
import exception.NotPositiveNumberException;
import exception.input.IindivisiblePurchaseBudgetException;
import exception.input.NonNumericInputException;
import exception.input.NotPositivePurchaseBudgetException;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Integer LOTTO_TICKET_PRICE = 1_000;

    private static final Scanner sc = new Scanner(System.in);

    public static Integer inputPurchaseAmount() {
        Integer purchaseBudget = null;
        while (purchaseBudget == null) {
            purchaseBudget = tryInputPurchaseBudget();
        }
        return purchaseBudget / LOTTO_TICKET_PRICE;
    }

    private static Integer tryInputPurchaseBudget() {
        try {
            System.out.println("구입 금액을 입력해 주세요.");
            int purchaseBudget = sc.nextInt();
            validatePurchaseBudget(purchaseBudget);
            return purchaseBudget;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        } catch (InputMismatchException e) {
            OutputView.printErrorMessage("숫자를 입력해주세요.");
            sc.nextLine();
        }
        return null;
    }

    private static void validatePurchaseBudget(final int purchaseBudget) {
        if (purchaseBudget <= 0)
            throw new NotPositivePurchaseBudgetException();

        if (purchaseBudget % LOTTO_TICKET_PRICE != 0)
            throw new IindivisiblePurchaseBudgetException();
    }

    public static WinningNumbersDto inputWinningNumbers() {
        return new WinningNumbersDto(inputWinningLottoNumbers(), inputBonusNumber());
    }

    private static List<Integer> inputWinningLottoNumbers() {
        sc.nextLine();
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        String[] tokens = sc.nextLine().split(",");
        for (String token : tokens) {
            validateIsNumber(token.trim());
        }

        return Arrays.stream(tokens)
                .map(token -> Integer.parseInt(token.trim()))
                .collect(Collectors.toList());
    }

    private static int inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return sc.nextInt();
    }

    private static void validateIsNumber(String token) {
        if (!token.matches("[0-9]+"))
            throw new NonNumericInputException();
    }

    public static int inputManualLottoNumber() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int number = sc.nextInt();
        validatePositiveNumber(number);
        return number;
    }

    private static void validatePositiveNumber(final int number) {
        if(number <= 0)
            throw new NotPositiveNumberException();
    }
}
