package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class LottoInputTemplate {
    private final Scanner scanner;

    public LottoInputTemplate() {
        scanner = new Scanner(System.in);
    }

    private String getNextInputLine() {
        return scanner.nextLine().trim();
    }

    public String inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return getNextInputLine();
    }

    public String inputManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return getNextInputLine();
    }

    public List<String> inputManualLottos(Integer manualLottoCount) {
        List<String> manualLottos = new ArrayList<>();
        if(manualLottoCount == 0) {
            return manualLottos;
        }
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        IntStream.range(0, manualLottoCount)
                .forEach(i -> manualLottos.add(getNextInputLine()));
        return manualLottos;
    }

    public String inputLottoWinningNumbers() {
        System.out.println("지난 주 당첨 번호 입력해 주세요.");
        return getNextInputLine();
    }

    public String inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return getNextInputLine();
    }
}
