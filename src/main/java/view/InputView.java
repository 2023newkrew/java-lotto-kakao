package view;

import domain.LottoNumber;
import domain.LottoTicket;
import domain.WinningLotto;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private final PrintStream outputStream;
    private final Scanner inputStream;

    public InputView(PrintStream outputStream, InputStream inputStream){
        this.outputStream = outputStream;
        this.inputStream = new Scanner(inputStream);
    }

    public int getPurchasePrice() {
        outputStream.println("구입금액을 입력해 주세요.");
        String purchasePrice = inputStream.nextLine();
        if(!isInteger(purchasePrice)){
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
        return Integer.parseInt(purchasePrice);
    }

    public WinningLotto getLastWinningLotto() {
        List<LottoNumber> lastLottoNumbers = getLastLottoNumbers();
        LottoTicket lastLottoTicket = new LottoTicket(lastLottoNumbers);
        LottoNumber bonusNumber = getLastBonusNumber();
        return new WinningLotto(lastLottoTicket, bonusNumber);
    }

    private List<LottoNumber> getLastLottoNumbers(){
        outputStream.println();
        outputStream.println("지난 주 당첨 번호를 입력해 주세요.");
        String lottoNumbers = inputStream.nextLine();
        List<String> splitNumbers = trim(split(lottoNumbers));

        if(!isPositiveIntegers(splitNumbers)){
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
        return toInteger(splitNumbers)
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private LottoNumber getLastBonusNumber(){
        outputStream.println("보너스 볼을 입력해 주세요.");
        String bonusLottoNumber = inputStream.nextLine();
        if(!isInteger(bonusLottoNumber)){
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
        return new LottoNumber(Integer.parseInt(bonusLottoNumber));
    }

    private boolean isPositiveIntegers(List<String> texts){
        return texts.stream()
                .allMatch(this::isInteger);
    }

    private boolean isInteger(String text){
        try{
            Integer.parseInt(text);
            return true;
        }catch (NumberFormatException numberFormatException){
            return false;
        }
    }

    private List<Integer> toInteger(List<String> numbers){
        return numbers.stream()
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private List<String> trim(List<String> numbers){
        return numbers.stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private List<String> split(String numbers){
        return List.of(numbers.split(","));
    }
}
