package lotto.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.model.Lotto;
import lotto.model.LottoNumber;
import lotto.model.Money;

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

    public LottoNumber inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int number = Integer.parseInt(scanner.nextLine());
        return new LottoNumber(number);
    }

    public int inputManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        String text = scanner.nextLine();
        return Integer.parseInt(text);
    }

    public List<Lotto> inputManualLottos(int count) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String text = scanner.nextLine();
            lottos.add(convertToLotto(text));
        }
        return lottos;
    }

    private static Lotto convertToLotto(String text) {
        Set<LottoNumber> lottoNumbers = Arrays.stream(text.split(","))
                .map(String::trim)
                .map(Integer::valueOf)
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
        return Lotto.create(lottoNumbers);
    }
}
