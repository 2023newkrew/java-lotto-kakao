package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoNumberPicker {

    private final List<LottoBall> lottoNumbers;

    public LottoNumberPicker() {
        this.lottoNumbers = initializeLottoNumbers();
    }

    private List<LottoBall> initializeLottoNumbers() {
        List<LottoBall> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            lottoNumbers.add(new LottoBall(i));
        }
        return lottoNumbers;
    }

    public LottoTicket makeLottoTicket() {
        Collections.shuffle(lottoNumbers);
        Set<LottoBall> selectedNumbers = lottoNumbers.stream()
                .limit(6).collect(Collectors.toSet());
        return new LottoTicket(selectedNumbers);
    }
}
