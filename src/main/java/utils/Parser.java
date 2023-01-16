package utils;

import exception.*;
import model.Lotto;
import model.LottoNumber;
import model.PurchaseMoney;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    private static final int LOTTO_NUMBER_COUNT = 6;

    /**
     * @throws LottoNumberDuplicateException - 입력된 로또 번호에 중복이 있을 경우 발생하는 예외
     */
    public static Lotto parsingStringToLotto(final String input) throws LottoNumberDuplicateException {
        return new Lotto(new HashSet<>(parsingStringToSixNumbers(input)));
    }

    /**
     * @throws LottoNumberCountException - 입력된 로또 번호가 6개가 아닐 경우 발생하는 예외
     */
    private static List<LottoNumber> parsingStringToSixNumbers(final String input) throws LottoNumberCountException {
        List<String> inputString = Arrays.stream(input.replace(" ", "").split(",")).collect(Collectors.toList());
        if (inputString.size() != LOTTO_NUMBER_COUNT) {
            throw new LottoNumberCountException();
        }
        return inputString.stream().map(Parser::parsingStringToLottoNumber).collect(Collectors.toList());
    }

    /**
     * @throws NumberFormatException - 입력이 숫자가 아닐 경우 발생하는 예외
     * @throws LottoNumberRangeException - 입력된 로또 번호가 1~45 사이의 값이 아닐경우 발생하는 예외
     */
    public static LottoNumber parsingStringToLottoNumber(final String input) throws NumberFormatException, LottoNumberRangeException{
        return LottoNumber.getLottoNumber(parsingStringToInt(input));
    }

    /**
     * @throws NumberFormatException - 입력이 숫자가 아닐 경우 발생하는 예외
     * @throws PurchaseMoneyException - 로또 구입 금액이 1000 미만이거나 1000의 배수가 아닐 경우 발생하는 예외
     */
    public static PurchaseMoney parsingStringToPurchaseMoney(final String input) throws NumberFormatException, PurchaseMoneyException{
        return new PurchaseMoney(Long.parseLong(input));
    }

    /**
     * @throws NumberFormatException - 입력이 숫자가 아닐 경우 발생하는 예외
     */
    public static Integer parsingStringToInt(final String input) throws NumberFormatException {
        return Integer.parseInt(input);
    }
}
