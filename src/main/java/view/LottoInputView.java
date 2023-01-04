package view;

import domain.lotto.Cost;
import domain.lotto.LottoNumber;
import domain.lotto.LottoWinningNumber;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LottoInputView {

    private static final Scanner sc = new Scanner(System.in);

    public Cost readCost() {
        System.out.println("구매금액을 입력해 주세요.");
        int inputCost = Integer.parseInt(sc.nextLine());

        return new Cost(inputCost);
    }

    public LottoWinningNumber readWinningNumber() {
        List<LottoNumber> lottoNumbers = readLottoNumbers();
        LottoNumber bonusNumber = readBonusNumber();

        return new LottoWinningNumber(lottoNumbers, bonusNumber);
    }

    private List<LottoNumber> readLottoNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        String input = sc.nextLine();
        String[] inputStrings = input.split(",");

        return Arrays.stream(inputStrings)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private LottoNumber readBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusBallNumber = Integer.parseInt(sc.nextLine());

        return new LottoNumber(bonusBallNumber);
    }
}
