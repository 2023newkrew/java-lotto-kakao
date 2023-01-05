package lotto.domain;

import java.util.ArrayList;
import java.util.List;

import static lotto.utils.Constants.*;

public class LottoTicket {
    private final List<Integer> lottoNumbers;

    public LottoTicket(ArrayList<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
        lottoNumberCountCheck();
        lottoNumberRangeCheck();
        lottoNumberDuplicateCheck();
    }

    public List<Integer> getLottoNumbers() {
        return this.lottoNumbers;
    }

    private void lottoNumberCountCheck() {
        if (lottoNumbers.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException("로또 번호의 개수가 6개가 아닙니다");
        }
    }

    private void lottoNumberRangeCheck(){
        for(int number : lottoNumbers){
            rangeCheck(number);
        }
    }

    public void rangeCheck(int number){
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