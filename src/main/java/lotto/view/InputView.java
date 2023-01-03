package lotto.view;

import lotto.utils.Validator;

import java.util.*;

public class InputView {

    private final Validator validator = new Validator();
    public int receiveMoneyUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("구입금액을 입력해 주세요.");
        int money = validator.stringToInteger(scanner.nextLine());
        validator.isThousandUnit(money);
        return money;
    }

    public List<Integer> receiveLastLottoNumbers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        List<Integer> lottoNumbers;
        lottoNumbers = validator.stringToIntgerList(scanner.nextLine());
        validator.checkSize(lottoNumbers);
        validator.checkDuplicate(lottoNumbers);
        checkLottoNumbersInRange(lottoNumbers);

        return lottoNumbers;
    }

    private void checkLottoNumbersInRange(List<Integer> lottoNumbers){
        for (int lottoNumber: lottoNumbers) {
            validator.checkInRange(lottoNumber);
        }
    }

    public int receiveLastLottoBonusNumber(List<Integer> lottoNumbers) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusNumber = validator.stringToInteger(scanner.nextLine());
        validator.checkInRange(bonusNumber);
        validator.checkDuplicateLottoNumberWithBonusNumber(lottoNumbers, bonusNumber);
        return bonusNumber;
    }
}
