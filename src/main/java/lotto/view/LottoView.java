package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LottoView {
    private final Scanner scanner;

    public LottoView() {
        scanner = new Scanner(System.in);
    }

    public int inputPurchase() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextInt();
    }

    public void printAmount(int amount) {
        System.out.println(amount + "개를 구매했습니다.");
    }

    public void printLotto(Lotto lotto) {
        System.out.println(lotto);
    }

    public void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos) {
            printLotto(lotto);
        }
    }

    public List<LottoNumber> inputWinNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return Arrays.stream(scanner.nextLine().replace(" ", "").split(","))
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public LottoNumber inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return new LottoNumber(scanner.nextInt());
    }
}
