package lottov2.view;

import lottov2.model.store.Money;
import lottov2.model.ticket.LottoNumber;
import lottov2.model.ticket.SingleLottoNumber;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoInputView {

    private final Scanner scanner;

    public LottoInputView() {
        scanner = new Scanner(System.in);
    }

    public Money inputMoney() {
        System.out.println("구매 금액을 입력해 주세요.");
        long amount = Long.parseLong(scanner.nextLine());

        return Money.valueOf(amount);
    }

    public LottoNumber inputWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String text = scanner.nextLine();

        return convertToLotto(text);
    }

    private static LottoNumber convertToLotto(String text) {
        Set<SingleLottoNumber> singleLottoNumbers = Arrays.stream(text.split(","))
                .map(String::trim)
                .map(Integer::valueOf)
                .map(SingleLottoNumber::valueOf)
                .collect(Collectors.toSet());

        return LottoNumber.of(singleLottoNumbers);
    }

    public SingleLottoNumber inputBonus() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int number = Integer.parseInt(scanner.nextLine());

        return SingleLottoNumber.valueOf(number);
    }
}
