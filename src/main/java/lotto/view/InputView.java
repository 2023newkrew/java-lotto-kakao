package lotto.view;

import java.util.*;
import java.util.stream.Collectors;

public class InputView {
    public int receiveMoneyUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("구입금액을 입력해 주세요.");
        int money = stringToInteger(scanner.nextLine());
        isThousandUnit(money);
        return money;
    }

    private int stringToInteger(String input) {
        int number;
        try {
            number = Integer.parseInt(input);
        } catch (Exception e){
            throw new IllegalArgumentException("숫자로 입력해야 합니다.");
        }
        return number;
    }

    private void isThousandUnit(int money) {
        if (money % 1000 != 0){
            throw new IllegalArgumentException("구매 금액은 1000원 단위로 입력해야 합니다.");
        }
    }

    public List<Integer> receiveLastLottoNumbers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        List<Integer> lottoNumbers;
        lottoNumbers = stringToIntgerList(scanner.nextLine());
        checkSize(lottoNumbers);
        checkDuplicate(lottoNumbers);

        return lottoNumbers;
    }

    private List<Integer> stringToIntgerList(String input) {
        List<Integer> numbers;
        try {
            numbers = Arrays.stream(input.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
        catch(Exception e){
            throw new IllegalArgumentException("숫자로 입력해야 합니다.");
        }
        return numbers;
    }

    private void checkSize(List<Integer> lottoNumbers) {
        if(lottoNumbers.size() != 6){
            throw new IllegalArgumentException("로또 번호는 6개를 입력해야 합니다.");
        }
    }

    private void checkDuplicate(List<Integer> lottoNumbers) {
        Set<Integer> splitedInputSet = new HashSet<>(lottoNumbers);
        if(splitedInputSet.size() < lottoNumbers.size()){
            throw new IllegalArgumentException("로또 번호는 중복 입력 해서는 안됩니다.");
        }
    }
}
