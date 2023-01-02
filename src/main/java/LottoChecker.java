import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

public class LottoChecker {

    public LottoResult getResult(LottoTicket lottoTicket, WinningNumbers winningNumbers){
        List<Integer> containedNumbers = lottoTicket.getLottoNumbers().stream()
                .filter((number)->winningNumbers.getLottoNumber().contains(number))
                .collect(Collectors.toList());

        return getRank(containedNumbers.size(),
                lottoTicket.getLottoNumbers().contains(winningNumbers.getBonusNumber()));
    }

    private LottoResult getRank(int size, boolean hasBonusNumber) {
        if(size < 3) return LottoResult.FAIL;
        if(size == 3) return LottoResult.FIFTH_PLACE;
        if(size == 4) return LottoResult.FOURTH_PLACE;
        if(hasBonusNumber) return LottoResult.SECOND_PLACE;
        if(size == 5) return LottoResult.THIRD_PLACE;
        return LottoResult.FIRST_PLACE;
    }
}
