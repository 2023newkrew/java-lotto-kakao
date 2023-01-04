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
        return Integer.parseInt(purchasePrice);
    }

    public WinningLotto getLastWinningLotto() {
        List<Integer> lastLottoNumbers = getLastLottoNumbers();
        LottoTicket lastLottoTicket = LottoTicket.of(lastLottoNumbers);
        LottoNumber bonusNumber = getLastBonusNumber();
        return new WinningLotto(lastLottoTicket, bonusNumber);
    }

    private List<Integer> getLastLottoNumbers(){
        outputStream.println();
        outputStream.println("지난 주 당첨 번호를 입력해 주세요.");
        String lottoNumbers = inputStream.nextLine();

        return toInteger(split(lottoNumbers));
    }

    private List<Integer> toInteger(List<String> numbers){
        return numbers.stream()
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private List<String> split(String numbers){
        return List.of(numbers.split(","));
    }

    private LottoNumber getLastBonusNumber(){
        outputStream.println("보너스 볼을 입력해 주세요.");
        String bonusLottoNumber = inputStream.nextLine();
        return new LottoNumber(Integer.parseInt(bonusLottoNumber));
    }
}
