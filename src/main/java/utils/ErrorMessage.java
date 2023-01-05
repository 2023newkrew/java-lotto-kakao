package utils;

public enum ErrorMessage {
    LOTTO_HAS_SIX_NUMBER("로또 숫자는 6개이어야 합니다."),
    DUPLICATED_LOTTO_NUMBERS("로또 숫자는 중복이 불가합니다."),
    DUPLICATED_LOTTO_NUMBER_AND_BONUS_NUMBER("보너스번호와 당첨번호는 중복될 수 없습니다."),
    OUT_OF_LOTTO_NUMBER_RANGE("로또 숫자는 1 이상 45 이하여야합니다."),
    NOT_ENOUGH_MONEY("로또를 구매하기 위한 최소 금액이 부족합니다."),
    LOTTO_NUMBER_IS_NUMERIC("숫자만 입력해주세요.")
    ;


    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
