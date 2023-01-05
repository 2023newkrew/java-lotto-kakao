package lotto.domain;


import java.util.*;
import java.util.stream.Collectors;
import static lotto.domain.constants.LottoConstants.*;
import static lotto.domain.constants.LottoStringForm.Korean.*;

/**
 * TotalResult gather LottoResult(s)
 */
public class TotalResult {
    private final Map<LottoResult, LottoCount> lottoResultCount = new HashMap<>();
    private Cash totalPrize = new Cash(0);
    private int trial = 0;

    public TotalResult(){
        for (int i = 0; i< ONE_TRIAL_BALL_COUNT; i++){
            lottoResultCount.put(LottoResult.get(i, false), new LottoCount(0));
            lottoResultCount.put(LottoResult.get(i, true), new LottoCount(0));
        }
        lottoResultCount.put(LottoResult.get(ONE_TRIAL_BALL_COUNT, false), new LottoCount(0));
    }
    public void add(LottoResult lottoResult) {
        trial++;
        addPrize(lottoResult);
        lottoResultCount.put(lottoResult, lottoResultCount.get(lottoResult).add(1));
    }

    private void addPrize(LottoResult lottoResult){
        List<Cash> prizes = Arrays.stream(WinningCondition.values())
                .map(v -> v.getPrizeIfMatch(lottoResult))
                .filter(v->v.compareTo(new Cash(NO_PRIZE_CASH))>0)
                .collect(Collectors.toList());
        totalPrize = totalPrize.plus((prizes.size()!=0 ? prizes.get(0) : new Cash(NO_PRIZE_CASH)));
        System.out.println(prizes+""+totalPrize);
    }

    public LottoCount getLottoCountOfResult(LottoResult lottoResult){
        return lottoResultCount.get(lottoResult);
    }

    public LottoCount getLottoCountOfResults(LottoResults lottoResults){
        LottoCount result = new LottoCount(0);
        for (Iterator<LottoResult> it = lottoResults.getIterator(); it.hasNext(); ) {
            result = result.add(getLottoCountOfResult(it.next()));
        }
        return result;
    }

    public double getSurplusRatio() throws ArithmeticException{
        if (trial == 0){
            throw new ArithmeticException();
        }
        return (double)(totalPrize.getCashValue()) / (trial*LOTTO_PRICE);
    }


    @Override
    public String toString(){
        String startString = TOTAL_RESULT_STARTING;
        return startString + statisticsString() + surplusRatioString();
    }


    private String statisticsString(){
        String[] strings = new String[WinningCondition.values().length];
        for (WinningCondition con : WinningCondition.values()){
            strings[con.getOrder()] = con + "- " +
                    getLottoCountOfResults(con.getLottoResults());
        }
        return String.join("\n", strings)+"\n";
    }

    private String surplusRatioString(){
        double surplusRatio = getSurplusRatio();
        return String.format(TOTAL_RESULT_RATIO,
                surplusRatio, (surplusRatio>1 ? TOTAL_RESULT_BENEFIT:TOTAL_RESULT_LOSS));
    }
}
