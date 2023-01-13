package view;

import domain.LottoNumber;
import domain.LottoNumbers;
import domain.WinningLotto;
import util.IntegerUtil;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {
    private final PrintStream outputStream;
    private final Scanner inputStream;

    public InputView(PrintStream outputStream, InputStream inputStream){
        this.outputStream = outputStream;
        this.inputStream = new Scanner(inputStream);
    }

    public int getUserAmount() {
        outputStream.println("구입금액을 입력해 주세요.");

        String purchasePrice = inputStream.nextLine();
        validateInteger(purchasePrice);

        return Integer.parseInt(purchasePrice);
    }

    public int getManualLottoCountToPurchase() {
        outputStream.println();
        outputStream.println("수동으로 구매할 로또 수를 입력해 주세요.");

        String maulPurchaseLottoNumber = inputStream.nextLine();
        validateInteger(maulPurchaseLottoNumber);

        return Integer.parseInt(maulPurchaseLottoNumber);
    }

    public List<LottoNumbers> getManualLottoNumbers(int lottoCount) {
        outputStream.println();
        outputStream.println("수동으로 구매할 번호를 입력해 주세요.");

        List<LottoNumbers> lottoNumbers = new LinkedList<>();
        IntStream.range(0, lottoCount)
                .forEach((lottoIndex) -> lottoNumbers.add(getLottoNumbers()));
        return lottoNumbers;
    }

    public WinningLotto getLastWinningLotto() {
        LottoNumbers lastLottoNumbers = getLastLottoNumbers();
        LottoNumber bonusNumber = getLastBonusNumber();

        return new WinningLotto(lastLottoNumbers, bonusNumber);
    }

    private LottoNumbers getLastLottoNumbers(){
        outputStream.println();
        outputStream.println("지난 주 당첨 번호를 입력해 주세요.");
        return getLottoNumbers();
    }

    private LottoNumbers getLottoNumbers() {
        List<String> splitNumbers = trim(split(inputStream.nextLine()));

        validateIntegers(splitNumbers);
        List<LottoNumber> lottoNumbers = IntegerUtil.toInteger(splitNumbers)
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        return new LottoNumbers(lottoNumbers);
    }

    private LottoNumber getLastBonusNumber(){
        outputStream.println("보너스 볼을 입력해 주세요.");

        String bonusLottoNumber = inputStream.nextLine();
        validateInteger(bonusLottoNumber);

        return new LottoNumber(Integer.parseInt(bonusLottoNumber));
    }

    private void validateIntegers(List<String> numbers){
        numbers.forEach(this::validateInteger);
    }

    private void validateInteger(String number) {
        if(!IntegerUtil.isInteger(number)){
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
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