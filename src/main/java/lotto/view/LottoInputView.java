package lotto.view;

import lotto.domain.UserAccount;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoWinningNumber;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LottoInputView {

    private static final Scanner sc = new Scanner(System.in);

    public UserAccount readUserAccount() {
        System.out.println("구매금액을 입력해 주세요.");
        int inputCost = Integer.parseInt(sc.nextLine());

        return new UserAccount(inputCost);
    }

    public LottoWinningNumber readWinningNumber() {
        List<LottoNumber> lottoNumbers = readWinningNumbers();
        LottoNumber bonusNumber = readBonusNumber();

        return new LottoWinningNumber(lottoNumbers, bonusNumber);
    }

    private List<LottoNumber> readWinningNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        String input = sc.nextLine();
        String[] inputStrings = input.split(",");

        return Arrays.stream(inputStrings)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toList());
    }

    private LottoNumber readBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusBallNumber = Integer.parseInt(sc.nextLine());

        return LottoNumber.from(bonusBallNumber);
    }

    public int readBuyingAmount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        return Integer.parseInt(sc.nextLine());
    }

    public List<LottoNumber> readLottoNumbers() {
        String input = sc.nextLine();
        String[] inputStrings = input.split(",");

        return Arrays.stream(inputStrings)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toList());
    }
}
