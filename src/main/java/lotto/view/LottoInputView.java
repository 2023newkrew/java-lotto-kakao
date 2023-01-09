package lotto.view;

import lotto.model.ranking.WinningNumbers;
import lotto.model.store.Money;
import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTicket;
import lotto.model.ticket.SingleLottoNumber;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

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

    public long inputManualCount(){
        System.out.println();
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        return Long.parseLong(scanner.nextLine());
    }

    public LottoTicket inputManualLottos(long count){
        System.out.println();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        List<LottoNumber> lottos = LongStream.range(0,count)
                .mapToObj(ignore->inputLotto())
                .collect(Collectors.toList());

        return LottoTicket.of(lottos);
    }

    public WinningNumbers inputWinningNumbers() {
        LottoNumber winningNumber = inputWinningNumber();
        SingleLottoNumber bonus = inputBonus();

        return WinningNumbers.of(winningNumber, bonus);
    }

    private LottoNumber inputWinningNumber() {
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

        return inputLotto();
    }

    private LottoNumber inputLotto() {
        String text = scanner.nextLine();

        Set<SingleLottoNumber> singleLottoNumbers = Arrays.stream(text.split(","))
                .map(String::trim)
                .map(Integer::valueOf)
                .map(SingleLottoNumber::valueOf)
                .collect(Collectors.toSet());

        return LottoNumber.of(singleLottoNumbers);
    }

    private SingleLottoNumber inputBonus() {
        System.out.println();
        System.out.println("보너스 볼을 입력해 주세요.");
        int number = Integer.parseInt(scanner.nextLine());

        return SingleLottoNumber.valueOf(number);
    }
}
