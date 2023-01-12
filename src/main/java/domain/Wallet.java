package domain;

public class Wallet {
    public static final String INVALID_AMOUNT_MSG = "금액은 양의 정수이어야 합니다.";
    private int remainAmount;

    private int usage;

    public Wallet(int amount) {
        validateAmount(amount);
        this.remainAmount = amount;
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

    public void use(int usedAmount){
        int updatedAmount = remainAmount - usedAmount;

        validateAmount(usedAmount);
        validateAmount(updatedAmount);

        usage += usedAmount;
        remainAmount -= usedAmount;
    }
}
