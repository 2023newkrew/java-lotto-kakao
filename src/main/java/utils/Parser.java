package utils;

import exception.ManualLottoCountException;
import exception.PurchaseMoneyException;
import model.Lotto;
import model.LottoNumber;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Parser {

    public static Lotto parsingStringToLotto(final String input) throws Exception {
        Set<LottoNumber> lottoNumberList = Arrays.stream(input.replace(" ", "")
                        .split(","))
                .map(Integer::parseInt)
                .map(LottoNumber::getLottoNumber)
                .collect(Collectors.toSet());
        return new Lotto(lottoNumberList);
    }

    public static LottoNumber parsingStringToLottoNumber(final String input) throws Exception {
        return LottoNumber.getLottoNumber(Integer.parseInt(input));
    }

    public static long parsingPurchaseMoney(final String input) {
        long purchaseMoney = 0;
        try {
            purchaseMoney = Long.parseLong(input);
        } catch (Exception e) {
            throw (e);
        }
        if (purchaseMoney < 0 || purchaseMoney % 1000 != 0) {
            throw new PurchaseMoneyException();
        }
        return purchaseMoney;
    }

    public static int parsingManualLottoCount(final String input, int totalLottoCount) {
        int manualLottoCount = 0;
        try {
            manualLottoCount = Integer.parseInt(input);
        } catch (Exception e) {
            throw (e);
        }
        if (manualLottoCount > totalLottoCount || manualLottoCount < 0) {
            throw new ManualLottoCountException();
        }
        return manualLottoCount;
    }
}
