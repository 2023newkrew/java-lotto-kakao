package view;

import domain.lotto.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoInputView {

    private static final Scanner sc = new Scanner(System.in);

    public Cost readCost() {
        System.out.println("구매금액을 입력해 주세요.");
        int inputCost = Integer.parseInt(sc.nextLine());

        return new Cost(inputCost);
    }

    public LottoTicketPurchaseAmount readLottoManualPurchaseAmount(Cost purchaseCost) {
        System.out.println(System.lineSeparator() + "수동으로 구매할 로또 수를 입력해주세요.");
        int purchaseAmount = Integer.parseInt(sc.nextLine());
        return new LottoTicketPurchaseAmount(purchaseAmount, purchaseCost);
    }

    public List<LottoNumbers> readManualLottoNumbers(LottoTicketPurchaseAmount lottoTicketPurchaseAmount) {
        if (lottoTicketPurchaseAmount.isZero()) {
            return Collections.emptyList();
        }
        System.out.println(System.lineSeparator() + "수동으로 구매할 로또 번호를 입력해 주세요.");
        return IntStream.range(0, lottoTicketPurchaseAmount.getPurchaseAmount())
                .mapToObj(idx -> readLottoNumbers())
                .collect(Collectors.toList());
    }

    public LottoWinningNumbers readLottoWinningNumbers() {
        System.out.println(System.lineSeparator() + "지난 주 당첨 번호를 입력해 주세요.");
        LottoNumbers winningNumbers = readLottoNumbers();
        System.out.println("보너스 볼을 입력해 주세요.");
        LottoNumber bonusNumber = readBonusNumber();
        return new LottoWinningNumbers(winningNumbers, bonusNumber);
    }

    private LottoNumbers readLottoNumbers() {
        String input = sc.nextLine();
        String[] inputStrings = input.split(",");
        Set<LottoNumber> lottoNumbers = Arrays.stream(inputStrings)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toSet());
        return new LottoNumbers(lottoNumbers);
    }

    private LottoNumber readBonusNumber() {
        int bonusBallNumber = Integer.parseInt(sc.nextLine());
        return new LottoNumber(bonusBallNumber);
    }
}
