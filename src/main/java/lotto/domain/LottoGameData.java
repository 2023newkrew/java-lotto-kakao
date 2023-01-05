package lotto.domain;

public class LottoGameData {
    private int leftoverMoney = 0;
    private int manualTicketCount = 0;
    private int randomTicketCount = 0;

    public void addManualTicketCount(int count) {
        manualTicketCount += count;
    }

    public void addRandomTicketCount(int count) {
        randomTicketCount += count;
    }

    public void saveLeftoverMoney(int leftoverMoney) {
        this.leftoverMoney += leftoverMoney;
    }

    public int getManualTicketCount() {
        return manualTicketCount;
    }

    public int getRandomTicketCount() {
        return randomTicketCount;
    }

    public int receiveLeftoverMoney() {
        int leftoverMoney = this.leftoverMoney;
        this.leftoverMoney = 0;
        return leftoverMoney;
    }
}
