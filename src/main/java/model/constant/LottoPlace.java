//등수의 정보를 저장한다
package model.constant;

public enum LottoPlace {
    FIRST_PLACE(2 * 1000 * 1000 * 1000),
    SECOND_PLACE(30 * 1000 * 1000),
    THIRD_PLACE(1500 * 1000),
    FOURTH_PLACE(50 * 1000),
    FIFTH_PLACE(5 * 1000),
    LOSE(0);

    long value;

    LottoPlace(long value) {
        this.value = value;
    }

    public long valueOf() {
        return value;
    }
}
