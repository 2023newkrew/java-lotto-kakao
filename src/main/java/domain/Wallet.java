package domain;

public class Wallet {
    public static final String INVALID_AMOUNT_MSG = "금액은 양의 정수이어야 합니다.";
    private int remainAmount;

    private int usage;

    public Wallet(int remainAmount) {
        validateAmount(remainAmount);
        this.remainAmount = remainAmount;
    }

    private static void validateAmount(int amount) {
        if(amount < 0){
            throw new IllegalArgumentException(INVALID_AMOUNT_MSG);
        }
    }

    public int getUsage() {
        return usage;
    }

    public int getRemainAmount() {
        return remainAmount;
    }

    public void use(int amount){
        int updateAmount = remainAmount - amount;

        validateAmount(amount);
        validateAmount(updateAmount);

        usage += amount;
        remainAmount -= amount;
    }
}
