package lotto;

import buyer.BuyerResult;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLotto {
    private static final int MATCH_FIVE = 5;

    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(String winningNumbersAsString, int bonusNumber) {
        this(parseStringToIntList(winningNumbersAsString), bonusNumber);
    }

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) throw new IllegalArgumentException("중복된 숫자가 존재합니다");

        this.winningNumbers = new Lotto(winningNumbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    private static List<Integer> parseStringToIntList(String text){
        String[] texts = text.split(",");
        return Arrays.stream(texts).map(t -> Integer.parseInt(t.strip())).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof WinningLotto)) return false;

        WinningLotto cp = (WinningLotto) obj;

        return this.winningNumbers.equals(cp.winningNumbers) && this.bonusNumber.equals(cp.bonusNumber);
    }

    public Rank getRank(Lotto lotto) {
        int count = 0;
        boolean bonusMatch = false;
        List<LottoNumber> lottoNumbers = lotto.getLottoNumbers();

        for (final LottoNumber number : winningNumbers.getLottoNumbers()) {
            count += lottoNumbers.contains(number) ? 1 : 0;
        }
        if (count == MATCH_FIVE) bonusMatch = isBonusMatch(lotto);

        return Rank.getRank(new LottoMatch(count, bonusMatch));
    }

    private boolean isBonusMatch(Lotto lotto) {
        return lotto.getLottoNumbers().contains(bonusNumber);
    }

    public BuyerResult getResult(List<Lotto> lotteries) {
        BuyerResult buyerResult = new BuyerResult();
        for (Lotto lotto : lotteries) {
            buyerResult.matches(getRank(lotto));
        }
        return buyerResult;
    }
}
