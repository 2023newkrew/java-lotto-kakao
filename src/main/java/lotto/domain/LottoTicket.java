package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicket {
    private static final int LOTTO_TICKET_SIZE = 6;
    public static final int LOTTO_LOWER_BOUND = 1;
    public static final int LOTTO_UPPER_BOUND = 45;
    private final ArrayList<Integer> lottoNumbers;

    public LottoTicket(ArrayList<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
        lottoNumberCountCheck();
        lottoNumberRangeCheck();
        lottoNumberDuplicateCheck();
    }

    public List<Integer> getLottoNumbers() {
        return Collections.unmodifiableList(this.lottoNumbers);
    }

    private void lottoNumberCountCheck() {
        if (lottoNumbers.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException("로또 번호의 개수가 6개가 아닙니다");
        }
    }

    private void lottoNumberRangeCheck(){
        this.lottoNumbers.forEach(this::rangeCheck);
    }

    private void rangeCheck(int number){
        if(number < LOTTO_LOWER_BOUND || number > LOTTO_UPPER_BOUND) {
            throw new IllegalArgumentException("로또 번호가 1 ~ 45 사이의 숫자여야 합니다.");
        }
    }

    private void lottoNumberDuplicateCheck() {
        if (lottoNumbers.stream().distinct().count() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException("로또 번호에 중복이 존재합니다.");
        }
    }
}