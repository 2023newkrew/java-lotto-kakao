package view;

import domain.dto.WinningNumbersDto;
import domain.lotto.LottoMetaData;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner sc = new Scanner(System.in);

    public static Integer inputPurchaseAmount() {
        Integer purchaseBudget = null;
        while (purchaseBudget == null) {
            purchaseBudget = tryInputPurchaseBudget();
        }
        return purchaseBudget / LottoMetaData.LOTTO_TICKET_PRICE;
    }

    private static Integer tryInputPurchaseBudget() {
        try {
            System.out.println("구입 금액을 입력해 주세요.");
            Integer purchaseBudget = sc.nextInt();
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

    private static void validatePurchaseBudget(final Integer purchaseBudget) {
        if (purchaseBudget == null || purchaseBudget == 0)
            throw new IllegalArgumentException("구입 금액을 입력해주세요. (" + LottoMetaData.LOTTO_TICKET_PRICE + "원 단위)");

        if (purchaseBudget % LottoMetaData.LOTTO_TICKET_PRICE != 0)
            throw new IllegalArgumentException("로또 가격으로 나누어 떨어지는 금액을 입력해주세요.(" + LottoMetaData.LOTTO_TICKET_PRICE + "원)");
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

    private static Integer inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return sc.nextInt();
    }

    private static void validateIsNumber(String token) {
        if (!token.matches("[0-9]+"))
            throw new IllegalArgumentException("숫자를 입력해주세요.");
    }
}
