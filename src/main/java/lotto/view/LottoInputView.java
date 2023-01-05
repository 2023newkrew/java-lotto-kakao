package lotto.view;

import java.util.Set;
import lotto.model.Lotto;
import lotto.model.LottoNumber;
import lotto.model.Money;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LottoInputView {

    private final Scanner scanner;

    public LottoInputView() {
        scanner = new Scanner(System.in);
    }

    public Money inputMoney() {
        System.out.println("구매 금액을 입력해 주세요.");
        long amount = Long.parseLong(scanner.nextLine());
        return new Money(amount);
    }

    public Lotto inputWinningLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String text = scanner.nextLine();
        return convertToLotto(text);
    }

    private static Lotto convertToLotto(String text) {
        Set<LottoNumber> lottoNumbers = Arrays.stream(text.split(","))
                .map(String::trim)
                .map(Integer::valueOf)
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
        return Lotto.create(lottoNumbers);
    }

    public LottoNumber inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int number = Integer.parseInt(scanner.nextLine());
        return new LottoNumber(number);
    }
}
