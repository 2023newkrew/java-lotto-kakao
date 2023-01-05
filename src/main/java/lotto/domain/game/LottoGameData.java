package lotto.domain.game;

public class LottoGameData {

    private int leftoverMoney = 0;

    public void saveLeftoverMoney(int leftoverMoney) {
        this.leftoverMoney += leftoverMoney;
    }

    public int receiveLeftoverMoney() {
        int leftoverMoney = this.leftoverMoney;
        this.leftoverMoney = 0;
        return leftoverMoney;
    }
}
