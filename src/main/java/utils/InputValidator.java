package utils;

import domain.Payment;

import static constant.ExceptionMessage.INVALID_NUMBER_OF_LOTTO_BUY_MANUALLY;

public class InputValidator {

    public static void validateNumberOfManualLotto(Payment payment, int num) {
        if (num > LottoCalculator.calculateNumberOfLotto(payment)) {
            throw new RuntimeException(INVALID_NUMBER_OF_LOTTO_BUY_MANUALLY);
        }
    }

}
