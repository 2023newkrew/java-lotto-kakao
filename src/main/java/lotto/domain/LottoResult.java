package lotto.domain;

import lotto.domain.exception.InvalidLottoResult;
import static lotto.domain.LottoConstants.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoResult {
    private final int matchCount;
    private final boolean matchBonus;

    private final static Map<Integer, LottoResult> cache = new HashMap<>();

    private LottoResult(int matchCount, boolean matchBonus){
        if (matchCount < 0 || matchCount > BALLCOUNT_LIMIT ||
                (matchCount==BALLCOUNT_LIMIT && matchBonus)){
            throw new InvalidLottoResult();
        }
        this.matchBonus = matchBonus;
        this.matchCount = matchCount;
    }

    private static int mapCode(int matchCount, boolean matchBonus){
        return matchCount*2 + (matchBonus?1:0);
    }
    public static LottoResult get(int matchCount, boolean matchBonus){
        if (!cache.containsKey(mapCode(matchCount, matchBonus))){
            cache.put(mapCode(matchCount, matchBonus), new LottoResult(matchCount, matchBonus));
        }
        return cache.get(mapCode(matchCount,matchBonus));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        LottoResult that = (LottoResult) o;
        return matchCount == that.matchCount && matchBonus == that.matchBonus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchCount, matchBonus);
    }

    @Override
    public String toString() {
        String result = matchCount + "개 일치";
        if (matchBonus){
            result += ", 보너스 볼 일치";
        }
        return result;
    }
}
