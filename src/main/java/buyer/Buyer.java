package buyer;

import lotto.Lotteries;
import lotto.Lottery;
import lotto.LotteryDTO;
import lotto.LotteryResult;

import java.util.List;

public class Buyer {
    private final Lotteries lotteries = new Lotteries();
    private final BuyingPlan buyingPlan;

    public Buyer(int budget, int manualCount) {
        this.buyingPlan = new BuyingPlan(budget, manualCount);
    }

    public void buyLottery(Lottery lottery) {
        if (buyingPlan.getTotalCount() <= lotteries.getCount())
            throw new IllegalStateException("구매 개수를 초과했습니다");

        lotteries.addLottery(lottery);
    }

    public int getManualCount() {
        return buyingPlan.getManualCount();
    }

    public int getAutoCount() {
        return buyingPlan.getAutoCount();
    }

    public int getTotalLotteryCount() {
        return buyingPlan.getTotalCount();
    }

    public List<LotteryDTO> getLotteries() {
        return lotteries.getLotteryDTOs();
    }

    public BuyerResult getBuyerResult(LotteryResult lotteryResult) {
        return new BuyerResult(lotteries, lotteryResult);
    }
}
