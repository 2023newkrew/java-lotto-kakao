package lotto.view;

import lotto.model.ranking.WinningNumbers;
import lotto.model.store.Money;
import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTicket;
import lotto.model.ticket.SingleLottoNumber;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class LottoInputView {

    private final Scanner scanner;

    public LottoInputView() {
        scanner = new Scanner(System.in);
    }

    public Money inputMoney() {
        Money money = null;
        while (Objects.isNull(money)) {
            money = tryInputMoney();
        }

        return money;
    }

    private Money tryInputMoney() {
        System.out.println();
        System.out.println("구매 금액을 입력해 주세요.");
        try {
            long amount = Long.parseLong(scanner.nextLine());
            validateAmount(amount);

            return Money.valueOf(amount);
        } catch (NumberFormatException ex) {
            System.out.println("숫자를 입력해 주세요.");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    private void validateAmount(long amount) {
        if (amount == 0L) {
            throw new IllegalArgumentException("구입 금액은 0원일 수 없습니다.");
        }
    }

    public long inputManualCount(long max) {
        long manualCount = -1;
        while (manualCount == -1) {
            manualCount = tryInputManualCount(max);
        }

        return manualCount;
    }

    private long tryInputManualCount(long max) {
        System.out.println();
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        try {
            long manualCount = Long.parseLong(scanner.nextLine());
            validateManualCount(manualCount, max);
            return manualCount;
        } catch (NumberFormatException ex) {
            System.out.println("숫자를 입력해 주세요.");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        return -1L;
    }

    private void validateManualCount(long manualCount, long max) {
        if (manualCount < 0) {
            throw new IllegalArgumentException("수동 구매 수량은 0개 이상입니다.");
        }
        if (manualCount > max) {
            throw new IllegalArgumentException("수동 구매 수량은 " + max + "개 이하입니다.");
        }
    }

    public LottoTicket inputManualLottos(long count) {
        System.out.println();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        List<LottoNumber> lottos = LongStream.range(0, count)
                .mapToObj(ignore -> inputLotto())
                .collect(Collectors.toList());

        return LottoTicket.of(lottos);
    }

    private LottoNumber inputLotto() {
        LottoNumber lotto = null;
        while (Objects.isNull(lotto)) {
            lotto = tryInputLotto();
        }

        return lotto;
    }

    private LottoNumber tryInputLotto() {
        String text = scanner.nextLine();

        try {
            Set<SingleLottoNumber> singleLottoNumbers = Arrays.stream(text.split(","))
                    .map(String::trim)
                    .map(Integer::valueOf)
                    .map(SingleLottoNumber::valueOf)
                    .collect(Collectors.toSet());

            return LottoNumber.of(singleLottoNumbers);
        } catch (NumberFormatException ex) {
            System.out.println("숫자를 입력해 주세요.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public WinningNumbers inputWinningNumbers() {
        WinningNumbers winningNumbers = null;
        while (Objects.isNull(winningNumbers)) {
            winningNumbers = tryInputWinningNumbers();
        }

        return winningNumbers;
    }

    public WinningNumbers tryInputWinningNumbers() {
        try {
            LottoNumber winningNumber = inputWinningNumber();
            SingleLottoNumber bonus = inputBonus();

            return WinningNumbers.of(winningNumber, bonus);
        } catch (NumberFormatException ex) {
            System.out.println("숫자를 입력해 주세요.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    private LottoNumber inputWinningNumber() {
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

        return inputLotto();
    }

    private SingleLottoNumber inputBonus() {
        System.out.println();
        System.out.println("보너스 볼을 입력해 주세요.");
        int number = Integer.parseInt(scanner.nextLine());

        return SingleLottoNumber.valueOf(number);
    }
}
