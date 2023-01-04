package domain;

public class Count {

    private final int count;
    public final boolean zero;
    public final int remains;

    public Count(String count, int totalCount) {
        validate(count, totalCount);
        this.count = Integer.parseInt(count);
        zero = this.count == 0;
        remains = totalCount - this.count;
    }

    private void validate(String manualLottoCount, int totalCount) {
        int count;
        try {
            count = Integer.parseInt(manualLottoCount);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자를 입력해주세요.");
        }
        if (count < 0) {
            throw new IllegalArgumentException("개수는 항상 양수입니다.");
        }
        if (count > totalCount) {
            throw new IllegalArgumentException("금액이 모자랍니다.");
        }
    }

    public int getCount() {
        return count;
    }

}
