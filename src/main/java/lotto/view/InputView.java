package lotto.view;

import lotto.model.WinningNumbers;
import lotto.utils.Validator;

import java.util.*;

public class InputView {

    private final Validator validator = new Validator();
    public int receiveMoneyUserInput() {
        System.out.println("구입금액을 입력해 주세요.");
        int money = receiveNumber();
        validator.isThousandUnit(money);
        return money;
    }
    public List<Integer> receiveLottoNumbers() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> lottoNumbers;
        lottoNumbers = validator.stringToIntgerList(scanner.nextLine());
        validator.checkSize(lottoNumbers);
        validator.checkDuplicate(lottoNumbers);
        validator.checkLottoNumbersInRange(lottoNumbers);
        return lottoNumbers;
    }

    public int receiveNumber(){
        Scanner scanner = new Scanner(System.in);
        int number = validator.stringToInteger(scanner.nextLine());
        return number;
    }

    public WinningNumbers receiveLastLottoNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        List<Integer> lottoNumbers = receiveLottoNumbers();

        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusNumber = receiveLastLottoBonusNumber(lottoNumbers);

        return new WinningNumbers(lottoNumbers, bonusNumber);
    }

    public int receiveLastLottoBonusNumber(List<Integer> lottoNumbers){
        int bonusNumber = receiveNumber();
        validator.checkInRange(bonusNumber);
        validator.checkDuplicateLottoNumberWithBonusNumber(lottoNumbers, bonusNumber);

        return bonusNumber;
    }

    public List<List<Integer>> receiveManualLottos(int numberOfLotto){
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int numberOfManualLottos = receiveNumber();
        validator.checkNumbersSize(numberOfManualLottos, numberOfLotto);

        return receiveManualLottosNumbers(numberOfManualLottos);
    }

    public List<List<Integer>> receiveManualLottosNumbers(int numberOfManualLottos){
        List<List<Integer>> manualLottosNumbers = new ArrayList<>();
        if (numberOfManualLottos > 0) {
            System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        }

        for (int i = 0; i < numberOfManualLottos; i++) {
            List<Integer> lottoNumbers = receiveLottoNumbers();
            manualLottosNumbers.add(lottoNumbers);
        }
        return manualLottosNumbers;
    }
}
