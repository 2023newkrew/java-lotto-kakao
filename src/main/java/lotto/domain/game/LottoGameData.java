package lotto.domain.game;

public class LottoGameData {

    private int leftoverMoney = 0;
    private int randomTicketCount = 0;
    private int manualTicketCount = 0;

    public void saveLeftoverMoney(int leftoverMoney) {
        this.leftoverMoney += leftoverMoney;
    }

    public int receiveLeftoverMoney() {
        int leftoverMoney = this.leftoverMoney;
        this.leftoverMoney = 0;
        return leftoverMoney;
    }

    public void addRandomTicketCount(int count) {
        randomTicketCount += count;
    }

    public int getRandomTicketCount() {
        return randomTicketCount;
    }

    public void addManualTicketCount(int count) {
        manualTicketCount += count;
    }

    public int getManualTicketCount() {
        return manualTicketCount;
    }
}
