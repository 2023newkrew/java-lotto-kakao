package view;

import domain.dto.WinningNumbersDto;
import domain.lotto.LottoConstant;
import domain.lotto.ticket.LottoTicket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner sc = new Scanner(System.in);

    public static Integer inputPurchaseAmount() {
        System.out.println("구입 금액을 입력해 주세요.");

        Integer purchaseBudget = sc.nextInt();
        validatePurchaseBudget(purchaseBudget);
        return purchaseBudget / LottoConstant.LOTTO_TICKET_PRICE;
    }

    private static void validatePurchaseBudget(Integer purchaseBudget) {
        if (purchaseBudget == null || purchaseBudget == 0)
            throw new IllegalArgumentException("구입 금액을 입력해주세요. (" + LottoConstant.LOTTO_TICKET_PRICE + "원)");

        if (purchaseBudget % LottoConstant.LOTTO_TICKET_PRICE != 0)
            throw new IllegalArgumentException("로또 가격으로 나누어 떨어지는 금액을 입력해주세요.(" + LottoConstant.LOTTO_TICKET_PRICE + "원)");
    }

    public static List<LottoTicket> inputManualLottoNumbers(Integer purchaseAmount) {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int manualLottoNumbersAmount = inputManualLottoCount(purchaseAmount);
        sc.nextLine();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        ArrayList<LottoTicket> manualLottoTickets = new ArrayList<>();

        for (int i = 0; i < manualLottoNumbersAmount; i++) {
            manualLottoTickets.add(new LottoTicket(inputNumbers()));
        }
        return manualLottoTickets;
    }

    private static int inputManualLottoCount(Integer purchaseAmount) {
        int manualLottoNumbersAmount = sc.nextInt();
        if (purchaseAmount < manualLottoNumbersAmount)
            throw new IllegalArgumentException("수동 구매 개수가 총 로또 개수보다 클 수는 없습니다.");

        return manualLottoNumbersAmount;
    }

    public static WinningNumbersDto inputWinningNumbers() {
        return new WinningNumbersDto(inputWinningLottoNumbers(), inputBonusNumber());
    }

    private static List<Integer> inputWinningLottoNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        return inputNumbers();
    }

    private static Integer inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return sc.nextInt();
    }

    private static void validateIsNumber(String token) {
        if (!token.matches("[0-9]+"))
            throw new IllegalArgumentException("숫자를 입력해주세요.");
    }

    private static List<Integer> inputNumbers() {
        String[] tokens = sc.nextLine().split(",");
        for (String token : tokens) {
            validateIsNumber(token.trim());
        }

        return Arrays.stream(tokens)
                .map(token -> Integer.parseInt(token.trim()))
                .collect(Collectors.toList());
    }
}
