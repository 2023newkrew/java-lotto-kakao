package lotto.model;

import lotto.exception.ErrorCode;
import lotto.exception.LottoException;
import lotto.service.LottoService;
import lotto.util.StringUtil;

public class PurchaseAmount {
    private final int purchaseAmount;

    public PurchaseAmount(String purchaseAmountString) {
        validateTypeOfPurchaseAmount(purchaseAmountString);
        Integer purchaseAmount = StringUtil.convertStringToInt(purchaseAmountString);
        validateInputPurchaseAmount(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    private void validateTypeOfPurchaseAmount(String purchaseAmount) {
        try {
            StringUtil.convertStringToInt(purchaseAmount);
        } catch (NumberFormatException e) {
            throw new LottoException(ErrorCode.INVALID_INPUT_TYPE_NOT_INTEGER);
        }
    }

    private void validateInputPurchaseAmount(Integer purchaseAmount) {
        if (purchaseAmount < 0) {
            throw new LottoException(ErrorCode.INVALID_PURCHASE_AMOUNT);
        }
        if (purchaseAmount < LottoService.getLottoPrice()) {
            throw new LottoException(ErrorCode.LACK_OF_MONEY);
        }
    }

    public int getLottoTicketCount(Integer lottoPrice) {
        return purchaseAmount / lottoPrice;
    }

    public Double calculateRateOfReturn(int totalRevenue) {
        return (double) totalRevenue / purchaseAmount;
    }
}
