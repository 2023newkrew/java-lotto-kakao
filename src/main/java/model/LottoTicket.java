/**
 * 사용자의 로또들을 가지고 있다.. 그뿐이다..
 *
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoTicket {
    private final List<Lotto> lottoList; //복권들의 리스트를 가지고 있다.

    private final int count;

    private final int totalPay;

    LottoTicket(List<Lotto> lottoList, int totalPay) {
        this.lottoList = new ArrayList<>(lottoList); //끊어 줄 것..(인자가 변경되어도 값이 변경될 수 없도록..)
        this.count = lottoList.size();
        this.totalPay = totalPay;
    }

    //public List<Lotto> getLottoList() { 복사해서 리턴.. ->이렇게 해도.. 값을 변경할 수 있나..?
    //    return ArrayList<>(lottoList);
    //}

    public int getCount() {
        return count;
    }

    public int getTotalPay() {
        return totalPay;
    }
/*
    public long getTotalLotteryAmount(final LottoWinner winNumbers) {
        long result = 0;
        for(Lotto lotto : lottoList) {
            result += lotto.getLotteryAmount((int)lotto.getMatchCount(winNumbers.getNumbers()), lotto.isMatchBonusNumber(winNumbers.getBonusNumber()));
        }
        return result;
    }

    public Map<Integer, Integer> getTotalResult(final LottoWinner winNumbers) {
        Map<Integer, Integer> result = new HashMap<>();

        for(Lotto lotto : lottoList) {
            int lotteryResult = lotto.getLotteryResult((int)lotto.getMatchCount(winNumbers.getNumbers()), lotto.isMatchBonusNumber(winNumbers.getBonusNumber()));
            result.put(lotteryResult, result.getOrDefault(lotteryResult, 0) + 1);
        }
      return result;
    }

    public double getTotalLotteryRate(final long amount, final long purchaseAmount) {
        return (double)amount/purchaseAmount;
    }
 */
}
