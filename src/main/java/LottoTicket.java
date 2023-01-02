import java.util.*;

public class LottoTicket {

    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_NUMBER_SIZE = 6;

    private final List<Integer> lottoNumbers = new ArrayList<>();

    public LottoTicket(List<Integer> lottoNumbers) {
        //숫자 중복 체크
        validateNumberDuplication(lottoNumbers);
        validateNumberRange(lottoNumbers);
        Collections.sort(lottoNumbers);
        this.lottoNumbers.addAll(lottoNumbers);
    }

    private void validateNumberDuplication(List<Integer> lottoNumbers) {
        HashSet<Integer> hs = new HashSet<>(lottoNumbers);
        if (hs.size() != LOTTO_NUMBER_SIZE) throw new IllegalArgumentException();
    }

    private void validateNumberRange(List<Integer> lottoNumbers) {
        lottoNumbers
                .forEach((number) -> {
                    if (number < 1 || number > 45) throw new IllegalArgumentException();
                });
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
