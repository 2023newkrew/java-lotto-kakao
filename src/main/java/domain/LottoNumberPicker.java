package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        List<LottoBall> selectedNumbers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            selectedNumbers.add((lottoNumbers.get(i)));
        }
        return new LottoTicket(selectedNumbers);
    }
}
