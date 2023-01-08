package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static domain.LottoConstant.LOTTO_TICKET_LENGTH;

public class LottoTicket {
    public static final String INVALID_LOTTO_TICKET_LENGTH_MSG = String.format("로또의 길이는 %d 이어야 합니다.", LOTTO_TICKET_LENGTH);
    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        validateLottoNumber(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateLottoNumber(List<LottoNumber> lottoNumbers) {
        if(isInValidLottoNumberLength(lottoNumbers)){
            throw new IllegalArgumentException(INVALID_LOTTO_TICKET_LENGTH_MSG);
        }
        if(hasDuplicatedLottoNumber(lottoNumbers)){
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private boolean hasDuplicatedLottoNumber(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> lottoNumberSet = new HashSet<>(lottoNumbers);
        return lottoNumberSet.size() != lottoNumbers.size();
    }

    private boolean isInValidLottoNumberLength(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.size() != LOTTO_TICKET_LENGTH;
    }

    public boolean contains(LottoNumber lottoNumber){
        return lottoNumbers.contains(lottoNumber);
    }

    public int size(){
        return lottoNumbers.size();
    }

    public List<LottoNumber> findUnMatchLottoNumbers(LottoTicket lottoTicket) {
        return lottoNumbers.stream()
                .filter(lottoNumber -> !lottoTicket.contains(lottoNumber))
                .collect(Collectors.toList());
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
