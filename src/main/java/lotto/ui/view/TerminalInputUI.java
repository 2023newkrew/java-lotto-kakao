package lotto.ui.view;

import lotto.core.LottoBall;
import lotto.core.LottoTicket;
import lotto.core.LottoWinningNumber;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminalInputUI {
    private final PrintStream printer;
    private final Scanner scanner;

    public TerminalInputUI(InputStream inputStream, OutputStream outputStream) {
        this.scanner = new Scanner(inputStream);
        this.printer = new PrintStream(outputStream);
    }

    public long scanPurchaseMoney() {
        printer.println("구입금액을 입력해 주세요.");
        return scanner.nextLong();
    }


    public List<LottoTicket> scanMultiManualLotto() {
        printer.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int count = scanner.nextInt();
        printer.println("수동으로 구매할 번호를 입력해 주세요.");
        return Stream.generate(this::scanManualLotto)
                .limit(count)
                .collect(Collectors.toList());
    }

    private LottoTicket scanManualLotto() {
        String userInput = scanner.next();
        return new LottoTicket(
                Arrays.stream(userInput.split(","))
                        .map(v -> new LottoBall(Integer.parseInt(v)))
                        .collect(Collectors.toList())
        );

    }

    public LottoWinningNumber scanWinningNumber() {
        printer.println("지난 주 당첨 번호를 입력해 주세요.");
        String[] lottoNumberStrings = scanner.next()
                .split(",");
        printer.println("보너스 볼을 입력해 주세요.");
        int bonusNumber = scanner.nextInt();
        return new LottoWinningNumber(
                Arrays.stream(lottoNumberStrings)
                        .map(v -> new LottoBall(Integer.parseInt(v)))
                        .collect(Collectors.toList())
                , new LottoBall(bonusNumber)
        );
    }

}
