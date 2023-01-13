package domain;

public class Wallet {
    private static final String INVALID_USED_AMOUNT_VALUE_EXCEPTION_MSG = "사용 금액은 양의 정수이어야 합니다.";
    private static final String LACK_OF_HOLDING_AMOUNT_EXCEPTION_MSG = "보유 금액이 부족합니다.";
    public static final int INIT_AMOUNT = 0;
    private int amount;

    private int usage;

    public Wallet(){
        this(INIT_AMOUNT);
    }

    public Wallet(int amount) {
        validateUsedAmount(amount);
        this.amount = amount;
    }

    private static void validateUsedAmount(int usedAmount) {
        if(usedAmount < 0){
            throw new IllegalArgumentException(INVALID_USED_AMOUNT_VALUE_EXCEPTION_MSG);
        }
    }

    private static void validateHoldingAmount(int holdingAmount) {
        if(holdingAmount < 0){
            throw new IllegalArgumentException(LACK_OF_HOLDING_AMOUNT_EXCEPTION_MSG);
        }
    }

    public void receiveMoney(int money){
        this.amount += money;
    }

    public void use(int usedAmount){
        int updatedHoldingAmount = amount - usedAmount;

        validateUsedAmount(usedAmount);
        validateHoldingAmount(updatedHoldingAmount);

        usage += usedAmount;
        amount -= usedAmount;
    }

    public int getUsage() {
        return usage;
    }

    public int getAmount() {
        return amount;
    }
}
