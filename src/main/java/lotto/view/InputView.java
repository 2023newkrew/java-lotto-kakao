package lotto.view;

import lotto.constant.Constants;
import lotto.utils.UserInputValidator;

import java.util.*;
import java.util.stream.Collectors;

/**
 * User Input을 받는 클래스입니다.
 * UserInputValidator 유틸클래스로 각 사용자 입력에 대한 validation check도 진행합니다.
 */
public class InputView {
    Scanner scanner;

    public int receiveMoneyUserInput() {
        scanner = new Scanner(System.in);
        System.out.println("구입금액을 입력해 주세요.");
        String moneyUserInput = scanner.nextLine();
        UserInputValidator.checkNullOrEmpty(moneyUserInput);
        int money = UserInputValidator.stringToInteger(moneyUserInput);
        UserInputValidator.checkUnderPriceOfLotto(money);
        System.out.println();

        return money;
    }


    public List<Integer> receiveWinLottoNumbers() {
        scanner = new Scanner(System.in);
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String lottoNumbersUserInput = scanner.nextLine();
        UserInputValidator.checkNullOrEmpty(lottoNumbersUserInput);
        List<Integer> lottoNumbers = UserInputValidator.stringToIntgerList(lottoNumbersUserInput);
        UserInputValidator.checkSize(lottoNumbers);
        UserInputValidator.checkDuplicate(lottoNumbers);
        UserInputValidator.checkLottoNumbersInRange(lottoNumbers);

        return Collections.unmodifiableList(lottoNumbers);
    }

    public int receiveWinBonusNumber(List<Integer> lottoNumbers) {
        scanner = new Scanner(System.in);
        System.out.println("보너스 볼을 입력해 주세요.");
        String bonusNumberUserInput = scanner.nextLine();
        UserInputValidator.checkNullOrEmpty(bonusNumberUserInput);
        int bonusNumber = UserInputValidator.stringToInteger(bonusNumberUserInput);
        System.out.println();
        UserInputValidator.checkInRange(bonusNumber);
        UserInputValidator.checkDuplicateLottoNumberWithBonusNumber(lottoNumbers, bonusNumber);

        return bonusNumber;
    }

    public int receiveNumberOfManualLotto(int money) {
        scanner = new Scanner(System.in);
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        String numberOfManualLottoUserInput = scanner.nextLine();
        UserInputValidator.checkNullOrEmpty(numberOfManualLottoUserInput);
        int numberOfManualLotto = UserInputValidator.stringToInteger(numberOfManualLottoUserInput);
        UserInputValidator.checkOverBudget(numberOfManualLotto, money);
        UserInputValidator.checkNegative(numberOfManualLotto);
        System.out.println();

        return numberOfManualLotto;
    }

    public List<List<Integer>> receiveAllManualLottoNumber(int numOfManualLotto) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<List<Integer>> manualLottoList = new ArrayList<>();
        for (int i = 0; i < numOfManualLotto; i++) {
            manualLottoList.add(receiveManualLottoNumber());
        }
        System.out.println();

        return Collections.unmodifiableList(manualLottoList);
    }

    public List<Integer> receiveManualLottoNumber() {
        scanner = new Scanner(System.in);
        String manualLottoNumbersUserInput = scanner.nextLine();
        UserInputValidator.checkNullOrEmpty(manualLottoNumbersUserInput);
        List<Integer> manualLottoNumbers = UserInputValidator.stringToIntgerList(manualLottoNumbersUserInput);
        UserInputValidator.checkSize(manualLottoNumbers);
        UserInputValidator.checkDuplicate(manualLottoNumbers);
        UserInputValidator.checkLottoNumbersInRange(manualLottoNumbers);

        return Collections.unmodifiableList(manualLottoNumbers);
    }
}
