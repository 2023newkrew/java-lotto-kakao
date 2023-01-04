package lotto.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Validator {

    public int stringToInteger(String input) {
        int number;
        try {
            number = Integer.parseInt(input);
        } catch (Exception e){
            throw new IllegalArgumentException("숫자로 입력해야 합니다.");
        }
        return number;
    }
    public void isThousandUnit(int money) {
        if (money % 1000 != 0){
            throw new IllegalArgumentException("구매 금액은 1000원 단위로 입력해야 합니다.");
        }
    }
    public List<Integer> stringToIntgerList(String input) {
        List<Integer> numbers;
        try {
            numbers = Arrays.stream(input.replaceAll("\\s", "")
                            .split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
        catch(Exception e){
            throw new IllegalArgumentException("숫자로 입력해야 합니다.");
        }
        return numbers;
    }
    public void checkSize(List<Integer> lottoNumbers) {
        if(lottoNumbers.size() != 6){
            throw new IllegalArgumentException("로또 번호는 6개를 입력해야 합니다.");
        }
    }
    public void checkDuplicate(List<Integer> lottoNumbers) {
        Set<Integer> splitedInputSet = new HashSet<>(lottoNumbers);
        if(splitedInputSet.size() < lottoNumbers.size()){
            throw new IllegalArgumentException("로또 번호는 중복 입력 해서는 안됩니다.");
        }
    }
    public void checkInRange(int number){
        if ( number < 1 || number > 45){
            throw new IllegalArgumentException("로또 번호는 1~45 사이입니다.");
        }
    }
    public void checkDuplicateLottoNumberWithBonusNumber(List<Integer> lottoNumbers, int bonusNumber){
        if (lottoNumbers.contains(bonusNumber)){
            throw new IllegalArgumentException("보너스 볼은 로또 번호와 중복되면 안됩니다.");
        }
    }
}
