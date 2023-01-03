package view;

import domain.lotto.LottoMetaData;

import java.util.Scanner;

public class InputView {

    private static final Scanner sc = new Scanner(System.in);

    public static Integer inputPurchaseAmount() {
        System.out.println("구입 금액을 입력해 주세요.");

        Integer purchaseBudget = sc.nextInt();
        validatePurchaseBudget(purchaseBudget);
        return purchaseBudget / LottoMetaData.LOTTO_TICKET_PRICE;
    }

    private static void validatePurchaseBudget(Integer purchaseBudget) {
        if (purchaseBudget == null || purchaseBudget == 0)
            throw new IllegalArgumentException("구입 금액을 입력해주세요. (" + LottoMetaData.LOTTO_TICKET_PRICE + "원)");

        if (purchaseBudget % LottoMetaData.LOTTO_TICKET_PRICE != 0)
            throw new IllegalArgumentException("로또 가격으로 나누어 떨어지는 금액을 입력해주세요.(" + LottoMetaData.LOTTO_TICKET_PRICE + "원)");
    }
}
