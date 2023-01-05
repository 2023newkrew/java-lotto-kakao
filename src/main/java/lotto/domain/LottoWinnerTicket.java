package lotto.domain;


import lotto.utils.LottoRank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static lotto.domain.LottoNumber.LOTTO_LOWER_BOUND;
import static lotto.domain.LottoNumber.LOTTO_UPPER_BOUND;

public class LottoWinnerTicket {
    private final LottoNumber winNumber;
    private final int bonusBall;


    public LottoWinnerTicket(LottoNumber winNumber, int bonusBall) {
        this.winNumber = winNumber;
        this.bonusBall = bonusBall;
        bonusRangeCheck();
    }

    private void bonusRangeCheck(){
        if(this.bonusBall < LOTTO_LOWER_BOUND || this.bonusBall > LOTTO_UPPER_BOUND) {
            throw new IllegalArgumentException("로또 번호가 1 ~ 45 사이의 숫자여야 합니다.");
        }
    }

    // 로또 통계 배열 생성
    public Map<LottoRank, Integer> getResult(LottoTicket userTicket) {
        List<LottoNumber> tickets = userTicket.getTicket();
        return tickets.stream().collect(Collectors.toMap(this::getScore, numbers -> 1, Integer::sum));
    }

    // 당첨 통계 배열 계산
    public LottoRank getScore (LottoNumber ticket) {
        int sameCount = checkSameCount(ticket);
        boolean isBonus = isBonusNumber(ticket, sameCount);
        return LottoRank.getRank(sameCount, isBonus);
    }

    // 하나의 로또 티켓 중 몇 개의 변호를 맞췄는지
    public int checkSameCount(LottoNumber userTicket) {
        return userTicket.getLottoNumbers().stream()
                .reduce(0, (sum, now) -> sum + checkContains(now));
    }

    // 보너스 숫자와 일치하는 것이 있는지 확인
    public boolean isBonusNumber(LottoNumber userTicket, int sameCount) {
        if(sameCount != 5) return true;
        return userTicket.getLottoNumbers().contains(bonusBall);
    }

    // 로또 티켓에 번호를 포함하고 있는지
    private int checkContains(int number){
        if(winNumber.getLottoNumbers().contains(number)) return 1;
        return 0;
    }

    // 수익률 계산
    public double calcRateOfReturn(int amount, Map<LottoRank, Integer> result) {
        long summary = getWinSummary(result);
        amount -= amount % 1000;
        return (double) summary / amount;
    }

    // 로또를 통해 얻은 총 수입 계산
    public long getWinSummary(Map<LottoRank, Integer> result) {
        // map을 돌면서 summary 구하기
        return result.entrySet()
                .stream()
                .mapToLong(rank -> rank.getKey().getPrize() * rank.getValue())
                .sum();
    }
}
