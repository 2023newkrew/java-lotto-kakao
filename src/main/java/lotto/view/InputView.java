package lotto.view;

import lotto.domain.LottoNumber;
import lotto.domain.LottoWinnerTicket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static lotto.utils.LottoMessage.*;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public int inputUserAmount(){
        System.out.println(INPUT_AMOUNT);
        return Integer.parseInt(scanner.nextLine());
    }

    public String inputWinNumber(){
        System.out.println(INPUT_WIN_NUMBERS);
        return scanner.nextLine();
    }

    public int inputBonusNumber(){
        System.out.println(INPUT_BONUS_BALL);
        return Integer.parseInt(scanner.nextLine());
    }

    private int inputManualCount(int amount){
        System.out.println(INPUT_MANUAL_COUNT);
        int manualCount = Integer.parseInt(scanner.nextLine());
        if(manualCount * 1000 > amount) {
            throw new IllegalArgumentException("수동으로 구매할 수 있는 로또 수는 구입 금액보다 적어야 합니다.");
        }
        return manualCount;
    }

    public List<LottoNumber> inputManualNumbers(int amount){
        int manualCount = inputManualCount(amount);
        System.out.println(INPUT_MANUAL_NUMBER);
        List<LottoNumber> ret = new ArrayList<>();
        for(int i=0; i<manualCount; i++){
            String userInput = scanner.nextLine();
            ret.add(changeToLottoNumber(userInput));
        }
        return ret;
    }

    // 사용자 입력값을 WinnerTicket 으로 변환
    public LottoWinnerTicket changeToWinnerTicket(String userInput, int bonus){
        Integer[] numbers = stringToArray(userInput);
        return new LottoWinnerTicket(
                new LottoNumber(Arrays.asList(numbers)), bonus);
    }

    public LottoNumber changeToLottoNumber(String userInput){
        Integer[] numbers = stringToArray(userInput);
        return new LottoNumber(Arrays.asList(numbers));
    }

    private Integer[] stringToArray(String userInput){
        return Stream.of(userInput
                        .replace(" ", "")
                        .split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toArray(Integer[]::new);
    }
}
