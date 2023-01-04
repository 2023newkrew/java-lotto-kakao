package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static domain.LottoConstant.LOTTO_LENGTH;

public class LottoTicket {
    private final List<LottoNumber> lottoNumbers;

    public static LottoTicket of(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .mapToInt(number -> number)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());

        return new LottoTicket(lottoNumbers);
    }

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        validateLottoNumber(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateLottoNumber(List<LottoNumber> lottoNumbers) {
        if(isInValidLottoNumberLength(lottoNumbers)){
            throw new IllegalArgumentException("로또의 길이는 " + LOTTO_LENGTH + "이어야 합니다.");
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
        return lottoNumbers.size() != LOTTO_LENGTH;
    }

    public boolean contains(LottoNumber lottoNumber){
        return lottoNumbers.contains(lottoNumber);
    }

    public int size(){
        return lottoNumbers.size();
    }

    public List<LottoNumber> findUnMatchLottoNumbers(LottoTicket lottoTicket) {
        return lottoNumbers.stream()
                .filter((lottoNumber) -> !lottoTicket.contains(lottoNumber))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "["
                + lottoNumbers.stream()
                .map(lottoNumber -> Integer.toString(lottoNumber.getNumber()))
                .collect(Collectors.joining(", "))
                + "]";
    }
}
