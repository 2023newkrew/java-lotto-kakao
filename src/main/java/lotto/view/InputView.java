package lotto.view;

import lotto.domain.LottoNumber;
import lotto.domain.LottoWinnerTicket;

import java.util.ArrayList;
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
        System.out.println(INPUT_AMOUNT.getMessage());
        return Integer.parseInt(scanner.nextLine());
    }

    public String inputWinNumber(){
        System.out.println(INPUT_WIN_NUMBERS.getMessage());
        return scanner.nextLine();
    }

    public int inputBonusNumber(){
        System.out.println(INPUT_BONUS_BALL.getMessage());
        return Integer.parseInt(scanner.nextLine());
    }

    public List<LottoNumber> inputManualNumbers(){
        System.out.println(INPUT_MANUAL_NUMBER.getMessage());
        int count = Integer.parseInt(scanner.nextLine());
        List<LottoNumber> ret = new ArrayList<>();
        for(int i=0; i<count; i++){
            String userInput = scanner.nextLine();
            ret.add(changeToLottoNumber(userInput));
        }
        return ret;
    }

    // 사용자 입력값을 WinnerTicket 으로 변환
    public LottoWinnerTicket changeToWinnerTicket(String userInput, int bonus){
        Integer[] numbers = stringToArray(userInput);
        return new LottoWinnerTicket(
                new LottoNumber(new ArrayList<>(List.of(numbers))), bonus);
    }

    public LottoNumber changeToLottoNumber(String userInput){
        Integer[] numbers = stringToArray(userInput);
        return new LottoNumber(new ArrayList<>(List.of(numbers)));
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
