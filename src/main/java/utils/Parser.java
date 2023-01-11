package utils;

import exception.ManualLottoCountException;
import model.Lotto;
import model.LottoNumber;
import model.PurchaseMoney;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Parser {

    public static Lotto parsingStringToLotto(final String input) throws IllegalArgumentException {
        Set<LottoNumber> lottoNumberList = Arrays.stream(input.replace(" ", "")
                        .split(","))
                .map(Integer::parseInt)
                .map(LottoNumber::getLottoNumber)
                .collect(Collectors.toSet());
        return new Lotto(lottoNumberList);
    }

    public static LottoNumber parsingStringToLottoNumber(final String input) throws IllegalArgumentException {
        return LottoNumber.getLottoNumber(Integer.parseInt(input));
    }

    public static PurchaseMoney parsingStringToPurchaseMoney(final String input) throws IllegalArgumentException {
        return new PurchaseMoney(Long.parseLong(input));
    }

    public static int parsingManualLottoCount(final String input, int totalLottoCount) {
        int manualLottoCount = 0;
        try {
            manualLottoCount = Integer.parseInt(input);
        } catch (Exception e) {
            throw (e);
        }
        if (manualLottoCount > totalLottoCount || manualLottoCount < 0) { //여기도 감싸서 수정할 것
            throw new ManualLottoCountException();
        }
        return manualLottoCount;
    }
}
