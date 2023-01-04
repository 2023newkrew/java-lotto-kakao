package lotto;

import buyer.BuyerResult;

import java.util.List;

public class LottoResult {
    private final LottoNumbers winningNumbers;
    private final LottoNumber bonusNumber;

    public LottoResult(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) throw new IllegalArgumentException("중복된 숫자가 존재합니다");

        this.winningNumbers = new LottoNumbers(winningNumbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LottoResult)) return false;

        LottoResult cp = (LottoResult) obj;

        return this.winningNumbers.equals(cp.winningNumbers) && this.bonusNumber.equals(cp.bonusNumber);
    }

    public Rank getRank(Lotto lotto) {
        int count = 0;
        List<LottoNumber> lottoNumbers = lotto.getLotteryNumber();

        for (final LottoNumber number : winningNumbers.getNumbers()) {
            count += lottoNumbers.contains(number) ? 1 : 0;
        }

        return Rank.getRank(new LottoMatch(count, isBonusMatch(lotto)));
    }

    private boolean isBonusMatch(Lotto lotto) {
        return lotto.getLotteryNumber().contains(bonusNumber);
    }

    public BuyerResult getResult(Lotteries lotteries) {
        BuyerResult buyerResult = new BuyerResult();
        for (Lotto lotto : lotteries.getLotteries()) {
            buyerResult.matches(getRank(lotto));
        }
        return buyerResult;
    }
}
